package com.lara.kaya.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "quick_meals")
data class QuickMealEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val name: String,
    val kcal: Int,
    val proteinGrams: Float,
    val emojiIcon: String
)
