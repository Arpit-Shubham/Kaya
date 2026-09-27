package com.lara.kaya.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.lara.kaya.data.model.MuscleGroup

@Entity(tableName = "workout_sets")
data class WorkoutSetEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val exerciseId: Long,
    val exerciseName: String,
    val muscleGroup: MuscleGroup,
    val weightKg: Float,
    val reps: Int,
    val durationSeconds: Long,
    val caloriesBurned: Float,
    val timestamp: Long = System.currentTimeMillis()
)
