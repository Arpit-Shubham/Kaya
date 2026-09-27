package com.lara.kaya.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import com.lara.kaya.data.local.dao.DailySummaryDao
import com.lara.kaya.data.local.dao.ExerciseDao
import com.lara.kaya.data.local.dao.IngredientDao
import com.lara.kaya.data.local.dao.NutritionDao
import com.lara.kaya.data.local.dao.QuickMealDao
import com.lara.kaya.data.local.dao.UserDao
import com.lara.kaya.data.local.dao.WorkoutDao
import com.lara.kaya.data.local.entity.DailySummaryEntity
import com.lara.kaya.data.local.entity.ExerciseEntity
import com.lara.kaya.data.local.entity.IngredientEntity
import com.lara.kaya.data.local.entity.LoggedMealEntity
import com.lara.kaya.data.local.entity.QuickMealEntity
import com.lara.kaya.data.local.entity.UserProfileEntity
import com.lara.kaya.data.local.entity.WorkoutSetEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(
    entities = [
        ExerciseEntity::class,
        IngredientEntity::class,
        QuickMealEntity::class,
        WorkoutSetEntity::class,
        LoggedMealEntity::class,
        DailySummaryEntity::class,
        UserProfileEntity::class
    ],
    version = 2,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class KayaDatabase : RoomDatabase() {

    abstract fun exerciseDao(): ExerciseDao
    abstract fun ingredientDao(): IngredientDao
    abstract fun quickMealDao(): QuickMealDao
    abstract fun workoutDao(): WorkoutDao
    abstract fun nutritionDao(): NutritionDao
    abstract fun dailySummaryDao(): DailySummaryDao
    abstract fun userDao(): UserDao

    companion object {
        @Volatile
        private var INSTANCE: KayaDatabase? = null

        fun getDatabase(context: Context): KayaDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    KayaDatabase::class.java,
                    "kaya_database"
                )
                .fallbackToDestructiveMigration()
                .addCallback(DatabaseCallback())
                .build()
                INSTANCE = instance
                instance
            }
        }

        private class DatabaseCallback : RoomDatabase.Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                INSTANCE?.let { database ->
                    CoroutineScope(Dispatchers.IO).launch {
                        try {
                            database.exerciseDao().insertAll(PreseedData.exercises)
                            database.ingredientDao().insertAll(PreseedData.ingredients)
                            database.quickMealDao().insertAll(PreseedData.quickMeals)
                            database.userDao().insertOrUpdateProfile(UserProfileEntity())
                        } catch (e: Exception) {
                            e.printStackTrace()
                        }
                    }
                }
            }
        }
    }
}
