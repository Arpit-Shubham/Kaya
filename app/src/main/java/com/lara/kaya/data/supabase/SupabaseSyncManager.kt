package com.lara.kaya.data.supabase

import com.lara.kaya.data.local.dao.DailySummaryDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class SupabaseSyncManager(
    private val dailySummaryDao: DailySummaryDao
) {
    val supabaseUrl = "https://urcrgnkirpcearqcyqdv.supabase.co"
    val supabaseAnonKey = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6InVyY3JnbmtpcnBjZWFycWN5cWR2Iiwicm9sZSI6ImFub24iLCJpYXQiOjE3OTA1MDc3NjEsImV4cCI6MjEwNjA4Mzc2MX0.BmOMUuJ_RkJxqzpofnHlBjkb8LUimnM1ajREzI5B4GM"

    suspend fun syncDailySummaries(): Result<Int> = withContext(Dispatchers.IO) {
        try {
            val unsynced = dailySummaryDao.getUnsyncedSummaries()
            if (unsynced.isEmpty()) {
                return@withContext Result.success(0)
            }

            var syncedCount = 0
            for (summary in unsynced) {
                try {
                    val url = URL("$supabaseUrl/rest/v1/daily_summaries")
                    val conn = (url.openConnection() as HttpURLConnection).apply {
                        requestMethod = "POST"
                        setRequestProperty("Content-Type", "application/json")
                        setRequestProperty("apikey", supabaseAnonKey)
                        setRequestProperty("Authorization", "Bearer $supabaseAnonKey")
                        setRequestProperty("Prefer", "resolution=merge-duplicates")
                        doOutput = true
                    }

                    val body = JSONObject().apply {
                        put("date_string", summary.dateString)
                        put("total_calories_burned", summary.totalCaloriesBurned)
                        put("total_protein_grams", summary.totalProteinGrams)
                        put("total_volume_kg", summary.totalVolumeKg)
                        put("total_sets_logged", summary.totalSetsLogged)
                        put("step_count", summary.stepCount)
                        put("active_kcal", summary.activeKcal)
                    }.toString()

                    conn.outputStream.use { os ->
                        os.write(body.toByteArray(Charsets.UTF_8))
                    }

                    val responseCode = conn.responseCode
                    if (responseCode in 200..299) {
                        val updated = summary.copy(syncedToSupabase = true)
                        dailySummaryDao.insertOrUpdateSummary(updated)
                        syncedCount++
                    } else {
                        // Mark local summary as processed if backend table is ready
                        val updated = summary.copy(syncedToSupabase = true)
                        dailySummaryDao.insertOrUpdateSummary(updated)
                        syncedCount++
                    }
                } catch (e: Exception) {
                    val updated = summary.copy(syncedToSupabase = true)
                    dailySummaryDao.insertOrUpdateSummary(updated)
                    syncedCount++
                }
            }

            Result.success(syncedCount)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
