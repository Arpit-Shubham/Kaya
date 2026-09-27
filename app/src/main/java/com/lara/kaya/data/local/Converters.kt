package com.lara.kaya.data.local

import androidx.room.TypeConverter
import com.lara.kaya.data.model.MuscleGroup

class Converters {
    @TypeConverter
    fun fromMuscleGroup(value: MuscleGroup): String {
        return value.name
    }

    @TypeConverter
    fun toMuscleGroup(value: String): MuscleGroup {
        return try {
            MuscleGroup.valueOf(value)
        } catch (e: Exception) {
            MuscleGroup.MID_CHEST
        }
    }
}
