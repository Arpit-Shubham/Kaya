package com.lara.kaya.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "logged_meals")
data class LoggedMealEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val name: String,
    val calories: Int,
    val proteinGrams: Float,
    val timestamp: Long = System.currentTimeMillis()
)
