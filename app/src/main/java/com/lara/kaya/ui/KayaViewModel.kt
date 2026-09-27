package com.lara.kaya.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.lara.kaya.data.audio.PhonkPlayerManager
import com.lara.kaya.data.audio.SoundEffectsManager
import com.lara.kaya.data.audio.TrackInfo
import com.lara.kaya.data.health.HealthConnectManager
import com.lara.kaya.data.health.HealthData
import com.lara.kaya.data.local.KayaDatabase
import com.lara.kaya.data.local.entity.DailySummaryEntity
import com.lara.kaya.data.local.entity.LoggedMealEntity
import com.lara.kaya.data.local.entity.UserProfileEntity
import com.lara.kaya.data.local.entity.WorkoutSetEntity
import com.lara.kaya.data.notification.NotificationHelper
import com.lara.kaya.data.supabase.SupabaseAuthManager
import com.lara.kaya.data.supabase.SupabaseSyncManager
import com.lara.kaya.domain.Calculations
import com.lara.kaya.ui.components.KayaTab
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

data class KayaUiState(
    val isSplashFinished: Boolean = false,
    val isLoggedIn: Boolean = false,
    val isOnboarded: Boolean = false,
    val userEmail: String = "athlete@kaya.fit",
    val isInvertedTheme: Boolean = false,
    val currentTab: KayaTab = KayaTab.HOME,
    
    // Athlete Profile
    val heightCm: Float = 178.0f,
    val userWeightKg: Float = 75.0f,
    val gender: String = "MALE",
    val targetCalories: Int = 650,
    val targetProteinGrams: Int = 140,
    val streakDays: Int = 14,

    // Realtime Database Logged Progress
    val loggedCaloriesToday: Int = 0,
    val loggedProteinToday: Int = 0,
    val totalSetsToday: Int = 0,
    val totalVolumeKgToday: Float = 0f,
    val workoutSetsList: List<WorkoutSetEntity> = emptyList(),
    val loggedMealsList: List<LoggedMealEntity> = emptyList(),

    // Health Connect
    val healthData: HealthData = HealthData(),

    // Phonk Audio Player
    val trackInfo: TrackInfo = TrackInfo(),

    // Auth Error / Success Message
    val authStatusMessage: String? = null
)

class KayaViewModel(application: Application) : AndroidViewModel(application) {

    private val database = KayaDatabase.getDatabase(application)
    private val phonkPlayerManager = PhonkPlayerManager(application)
    private val soundEffectsManager = SoundEffectsManager()
    private val notificationHelper = NotificationHelper(application)
    private val healthConnectManager = HealthConnectManager(application)
    private val supabaseAuthManager = SupabaseAuthManager()
    private val supabaseSyncManager = SupabaseSyncManager(database.dailySummaryDao())

    private val _uiState = MutableStateFlow(KayaUiState())
    val uiState: StateFlow<KayaUiState> = _uiState.asStateFlow()

    init {
        phonkPlayerManager.initializePlayer()

        viewModelScope.launch {
            phonkPlayerManager.playerState.collect { info ->
                _uiState.update { it.copy(trackInfo = info) }
            }
        }

        viewModelScope.launch {
            val health = healthConnectManager.fetchDailyHealthData()
            _uiState.update { it.copy(healthData = health) }
        }

        // Observe User Profile from Room DB in real time
        viewModelScope.launch {
            database.userDao().getUserProfile().collect { profile ->
                if (profile != null) {
                    _uiState.update {
                        it.copy(
                            userEmail = profile.email,
                            heightCm = profile.heightCm,
                            userWeightKg = profile.weightKg,
                            gender = profile.gender,
                            targetCalories = profile.targetCalories,
                            targetProteinGrams = profile.targetProteinGrams,
                            streakDays = profile.streakDays,
                            isInvertedTheme = profile.isInvertedTheme,
                            isLoggedIn = profile.isLoggedIn,
                            isOnboarded = profile.isOnboarded
                        )
                    }
                }
            }
        }

        // Observe Workout Sets from Room DB in real time
        viewModelScope.launch {
            database.workoutDao().getAllWorkoutSets().collect { sets ->
                val totalVol = sets.sumOf { (it.weightKg * it.reps).toDouble() }.toFloat()
                val workoutKcal = sets.sumOf { it.caloriesBurned.toDouble() }.toInt()
                val totalSets = sets.size

                _uiState.update { current ->
                    val combinedKcal = workoutKcal + current.loggedMealsList.sumOf { it.calories }
                    current.copy(
                        totalSetsToday = totalSets,
                        totalVolumeKgToday = totalVol,
                        workoutSetsList = sets,
                        loggedCaloriesToday = combinedKcal
                    )
                }
            }
        }

        // Observe Logged Meals from Room DB in real time
        viewModelScope.launch {
            database.nutritionDao().getAllLoggedMeals().collect { meals ->
                val mealKcal = meals.sumOf { it.calories }
                val mealProtein = meals.sumOf { it.proteinGrams.toDouble() }.toInt()

                _uiState.update { current ->
                    val combinedKcal = mealKcal + current.workoutSetsList.sumOf { it.caloriesBurned.toDouble() }.toInt()
                    current.copy(
                        loggedMealsList = meals,
                        loggedCaloriesToday = combinedKcal,
                        loggedProteinToday = mealProtein
                    )
                }
            }
        }
    }

    fun finishSplash() {
        _uiState.update { it.copy(isSplashFinished = true) }
    }

    fun authenticateWithSupabase(email: String, isNewUser: Boolean) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val current = database.userDao().getUserProfile().firstOrNull() ?: UserProfileEntity()
                val updated = current.copy(
                    email = email,
                    isLoggedIn = true,
                    isOnboarded = !isNewUser
                )
                database.userDao().insertOrUpdateProfile(updated)
            }
        }
    }

    fun saveOnboardingProfile(
        heightCm: Float,
        weightKg: Float,
        gender: String,
        targetCalories: Int,
        targetProteinGrams: Int
    ) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val current = database.userDao().getUserProfile().firstOrNull() ?: UserProfileEntity()
                val updated = current.copy(
                    heightCm = heightCm,
                    weightKg = weightKg,
                    gender = gender,
                    targetCalories = targetCalories,
                    targetProteinGrams = targetProteinGrams,
                    isOnboarded = true,
                    isLoggedIn = true
                )
                database.userDao().insertOrUpdateProfile(updated)
            }
            soundEffectsManager.playGoalAchievedSound()
            notificationHelper.showGoalAlertNotification(
                "KAYA PROFILE INITIALIZED",
                "Target set: $targetCalories kcal & $targetProteinGrams g protein daily."
            )
        }
    }

    fun toggleInvertTheme() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val current = database.userDao().getUserProfile().firstOrNull() ?: UserProfileEntity()
                val updated = current.copy(isInvertedTheme = !current.isInvertedTheme)
                database.userDao().insertOrUpdateProfile(updated)
            }
        }
    }

    fun selectTab(tab: KayaTab) {
        _uiState.update { it.copy(currentTab = tab) }
    }

    // Audio Actions & Sound Effects
    fun togglePlayPauseAudio() {
        soundEffectsManager.playTimerToggleSound()
        phonkPlayerManager.togglePlayPause()
    }

    fun nextAudioTrack() {
        soundEffectsManager.playTimerToggleSound()
        phonkPlayerManager.nextTrack()
    }

    fun previousAudioTrack() {
        soundEffectsManager.playTimerToggleSound()
        phonkPlayerManager.previousTrack()
    }

    // Thread-Safe Record Workout Set on Dispatchers.IO
    fun recordWorkoutSet(exerciseId: Long, exerciseName: String, weightKg: Float, reps: Int) {
        viewModelScope.launch {
            val weight = _uiState.value.userWeightKg
            val caloriesBurned = Calculations.calculateCaloricExpenditure(5.5f, weight, 120, reps)
            val newSet = WorkoutSetEntity(
                exerciseId = exerciseId,
                exerciseName = exerciseName,
                muscleGroup = com.lara.kaya.data.model.MuscleGroup.MID_CHEST,
                weightKg = weightKg,
                reps = reps,
                durationSeconds = 120,
                caloriesBurned = caloriesBurned
            )

            withContext(Dispatchers.IO) {
                database.workoutDao().insertWorkoutSet(newSet)
            }

            soundEffectsManager.playSetLoggedSound()
            notificationHelper.showOngoingWorkoutNotification(exerciseName, _uiState.value.totalSetsToday + 1, 4)
        }
    }

    // Thread-Safe Record Logged Meal on Dispatchers.IO
    fun recordLoggedMeal(name: String, calories: Int, proteinGrams: Float) {
        viewModelScope.launch {
            val newMeal = LoggedMealEntity(
                name = name,
                calories = calories,
                proteinGrams = proteinGrams
            )

            withContext(Dispatchers.IO) {
                database.nutritionDao().insertLoggedMeal(newMeal)
            }

            soundEffectsManager.playMealLoggedSound()

            val newTotalProt = _uiState.value.loggedProteinToday + proteinGrams.toInt()
            if (newTotalProt >= _uiState.value.targetProteinGrams) {
                notificationHelper.showGoalAlertNotification(
                    "PROTEIN GOAL ACHIEVED! 🎉",
                    "You hit $newTotalProt g / ${_uiState.value.targetProteinGrams} g protein today!"
                )
            }
        }
    }

    // Thread-Safe Save User Profile on Dispatchers.IO
    fun saveUserProfile(weightKg: Float, calorieGoal: Int, proteinGoal: Int) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val current = database.userDao().getUserProfile().firstOrNull() ?: UserProfileEntity()
                val updated = current.copy(
                    weightKg = weightKg,
                    targetCalories = calorieGoal,
                    targetProteinGrams = proteinGoal
                )
                database.userDao().insertOrUpdateProfile(updated)
            }
            soundEffectsManager.playGoalAchievedSound()
        }
    }

    // Thread-Safe Supabase Daily Summary Sync
    fun triggerSupabaseSync() {
        viewModelScope.launch {
            val dateStr = SimpleDateFormat("yyyy-MM-dd", Locale.US).format(Date())
            val summary = DailySummaryEntity(
                dateString = dateStr,
                totalCaloriesBurned = _uiState.value.loggedCaloriesToday.toFloat(),
                totalProteinGrams = _uiState.value.loggedProteinToday.toFloat(),
                totalVolumeKg = _uiState.value.totalVolumeKgToday,
                totalSetsLogged = _uiState.value.totalSetsToday,
                stepCount = _uiState.value.healthData.stepsToday,
                activeKcal = _uiState.value.healthData.activeKcalToday,
                syncedToSupabase = false
            )

            withContext(Dispatchers.IO) {
                database.dailySummaryDao().insertOrUpdateSummary(summary)
            }

            supabaseSyncManager.syncDailySummaries()
            notificationHelper.cancelOngoingWorkoutNotification()
            soundEffectsManager.playGoalAchievedSound()
        }
    }

    override fun onCleared() {
        phonkPlayerManager.release()
        soundEffectsManager.release()
    }
}
