package com.lara.kaya.data.supabase

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class SupabaseAuthManager {

    val supabaseUrl = "https://urcrgnkirpcearqcyqdv.supabase.co"
    val supabaseAnonKey = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6InVyY3JnbmtpcnBjZWFycWN5cWR2Iiwicm9sZSI6ImFub24iLCJpYXQiOjE3OTA1MDc3NjEsImV4cCI6MjEwNjA4Mzc2MX0.BmOMUuJ_RkJxqzpofnHlBjkb8LUimnM1ajREzI5B4GM"

    suspend fun signUpWithEmail(email: String, pass: String): Result<String> = withContext(Dispatchers.IO) {
        try {
            val url = URL("$supabaseUrl/auth/v1/signup")
            val conn = (url.openConnection() as HttpURLConnection).apply {
                requestMethod = "POST"
                setRequestProperty("Content-Type", "application/json")
                setRequestProperty("apikey", supabaseAnonKey)
                doOutput = true
            }

            val body = JSONObject().apply {
                put("email", email)
                put("password", pass)
            }.toString()

            conn.outputStream.use { os ->
                os.write(body.toByteArray(Charsets.UTF_8))
            }

            val responseCode = conn.responseCode
            if (responseCode in 200..299) {
                val reader = BufferedReader(InputStreamReader(conn.inputStream))
                val response = reader.readText()
                val json = JSONObject(response)
                val id = json.optJSONObject("user")?.optString("id") ?: email
                Result.success(id)
            } else {
                val reader = BufferedReader(InputStreamReader(conn.errorStream ?: conn.inputStream))
                val errorMsg = reader.readText()
                Result.failure(Exception("Sign Up Error ($responseCode): $errorMsg"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun signInWithEmail(email: String, pass: String): Result<String> = withContext(Dispatchers.IO) {
        try {
            val url = URL("$supabaseUrl/auth/v1/token?grant_type=password")
            val conn = (url.openConnection() as HttpURLConnection).apply {
                requestMethod = "POST"
                setRequestProperty("Content-Type", "application/json")
                setRequestProperty("apikey", supabaseAnonKey)
                doOutput = true
            }

            val body = JSONObject().apply {
                put("email", email)
                put("password", pass)
            }.toString()

            conn.outputStream.use { os ->
                os.write(body.toByteArray(Charsets.UTF_8))
            }

            val responseCode = conn.responseCode
            if (responseCode in 200..299) {
                val reader = BufferedReader(InputStreamReader(conn.inputStream))
                val response = reader.readText()
                val json = JSONObject(response)
                val token = json.optString("access_token", email)
                Result.success(token)
            } else {
                val reader = BufferedReader(InputStreamReader(conn.errorStream ?: conn.inputStream))
                val errorMsg = reader.readText()
                Result.failure(Exception("Sign In Error ($responseCode): $errorMsg"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
