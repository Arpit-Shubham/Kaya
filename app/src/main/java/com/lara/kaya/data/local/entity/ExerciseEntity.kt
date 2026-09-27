package com.lara.kaya.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.lara.kaya.data.model.MuscleGroup

@Entity(tableName = "exercises")
data class ExerciseEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val name: String,
    val muscleGroup: MuscleGroup,
    val defaultMET: Float,
    val recoveryHours: Int
)
