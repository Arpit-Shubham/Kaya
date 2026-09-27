package com.lara.kaya.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "daily_summaries")
data class DailySummaryEntity(
    @PrimaryKey
    val dateString: String, // format YYYY-MM-DD
    val totalCaloriesBurned: Float,
    val totalProteinGrams: Float,
    val totalVolumeKg: Float,
    val totalSetsLogged: Int,
    val stepCount: Int,
    val activeKcal: Int,
    val syncedToSupabase: Boolean = false
)
