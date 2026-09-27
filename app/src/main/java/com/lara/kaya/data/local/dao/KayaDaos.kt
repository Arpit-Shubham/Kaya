package com.lara.kaya.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.lara.kaya.data.local.entity.DailySummaryEntity
import com.lara.kaya.data.local.entity.ExerciseEntity
import com.lara.kaya.data.local.entity.IngredientEntity
import com.lara.kaya.data.local.entity.LoggedMealEntity
import com.lara.kaya.data.local.entity.QuickMealEntity
import com.lara.kaya.data.local.entity.UserProfileEntity
import com.lara.kaya.data.local.entity.WorkoutSetEntity
import com.lara.kaya.data.model.MuscleGroup
import kotlinx.coroutines.flow.Flow

@Dao
interface ExerciseDao {
    @Query("SELECT * FROM exercises")
    fun getAllExercises(): Flow<List<ExerciseEntity>>

    @Query("SELECT * FROM exercises WHERE muscleGroup = :group")
    fun getExercisesByGroup(group: MuscleGroup): Flow<List<ExerciseEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(exercises: List<ExerciseEntity>)
}

@Dao
interface IngredientDao {
    @Query("SELECT * FROM ingredients ORDER BY name ASC")
    fun getAllIngredients(): Flow<List<IngredientEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(ingredients: List<IngredientEntity>)
}

@Dao
interface QuickMealDao {
    @Query("SELECT * FROM quick_meals")
    fun getAllQuickMeals(): Flow<List<QuickMealEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(meals: List<QuickMealEntity>)
}

@Dao
interface WorkoutDao {
    @Query("SELECT * FROM workout_sets ORDER BY timestamp DESC")
    fun getAllWorkoutSets(): Flow<List<WorkoutSetEntity>>

    @Query("SELECT * FROM workout_sets WHERE timestamp >= :startTime ORDER BY timestamp DESC")
    fun getWorkoutSetsSince(startTime: Long): Flow<List<WorkoutSetEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertWorkoutSet(set: WorkoutSetEntity)
}

@Dao
interface NutritionDao {
    @Query("SELECT * FROM logged_meals ORDER BY timestamp DESC")
    fun getAllLoggedMeals(): Flow<List<LoggedMealEntity>>

    @Query("SELECT * FROM logged_meals WHERE timestamp >= :startTime ORDER BY timestamp DESC")
    fun getLoggedMealsSince(startTime: Long): Flow<List<LoggedMealEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertLoggedMeal(meal: LoggedMealEntity)
}

@Dao
interface DailySummaryDao {
    @Query("SELECT * FROM daily_summaries WHERE dateString = :dateLimit")
    fun getSummaryForDate(dateLimit: String): Flow<DailySummaryEntity?>

    @Query("SELECT * FROM daily_summaries ORDER BY dateString DESC LIMIT 7")
    fun getRecentSummaries(): Flow<List<DailySummaryEntity>>

    @Query("SELECT * FROM daily_summaries WHERE syncedToSupabase = 0")
    fun getUnsyncedSummaries(): List<DailySummaryEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertOrUpdateSummary(summary: DailySummaryEntity)
}

@Dao
interface UserDao {
    @Query("SELECT * FROM user_profile WHERE id = 1")
    fun getUserProfile(): Flow<UserProfileEntity?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertOrUpdateProfile(profile: UserProfileEntity)
}
