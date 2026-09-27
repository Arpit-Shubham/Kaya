package com.lara.kaya.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_profile")
data class UserProfileEntity(
    @PrimaryKey
    val id: Int = 1,
    val email: String = "athlete@kaya.fit",
    val heightCm: Float = 178.0f,
    val weightKg: Float = 75.0f,
    val gender: String = "MALE",
    val targetCalories: Int = 650,
    val targetProteinGrams: Int = 140,
    val streakDays: Int = 14,
    val isInvertedTheme: Boolean = false,
    val isLoggedIn: Boolean = false,
    val isOnboarded: Boolean = false
)
