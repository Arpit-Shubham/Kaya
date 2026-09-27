package com.lara.kaya

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.lara.kaya.ui.KayaViewModel
import com.lara.kaya.ui.components.KayaBottomNavigation
import com.lara.kaya.ui.components.KayaTab
import com.lara.kaya.ui.components.KayaTopBar
import com.lara.kaya.ui.screens.*
import com.lara.kaya.ui.theme.KayaTheme

class MainActivity : ComponentActivity() {

    private val viewModel: KayaViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            val state by viewModel.uiState.collectAsState()

            KayaTheme(inverted = state.isInvertedTheme) {
                if (!state.isSplashFinished) {
                    SplashScreen(
                        onSplashFinished = { viewModel.finishSplash() }
                    )
                } else if (!state.isLoggedIn) {
                    LoginScreen(
                        onLoginSuccess = { email, isNewUser ->
                            viewModel.authenticateWithSupabase(email, isNewUser)
                        }
                    )
                } else if (!state.isOnboarded) {
                    OnboardingScreen(
                        onOnboardingComplete = { h, w, g, c, p ->
                            viewModel.saveOnboardingProfile(h, w, g, c, p)
                        }
                    )
                } else {
                    Scaffold(
                        modifier = Modifier.fillMaxSize(),
                        containerColor = MaterialTheme.colorScheme.background,
                        topBar = {
                            KayaTopBar(
                                streakDays = state.streakDays,
                                isInverted = state.isInvertedTheme,
                                onToggleInvert = { viewModel.toggleInvertTheme() }
                            )
                        },
                        bottomBar = {
                            KayaBottomNavigation(
                                currentTab = state.currentTab,
                                onTabSelected = { viewModel.selectTab(it) }
                            )
                        }
                    ) { innerPadding ->
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(innerPadding)
                                .background(MaterialTheme.colorScheme.background)
                        ) {
                            when (state.currentTab) {
                                KayaTab.HOME -> {
                                    val calPerc = if (state.targetCalories > 0) ((state.loggedCaloriesToday.toFloat() / state.targetCalories) * 100).toInt() else 0
                                    val protPerc = if (state.targetProteinGrams > 0) ((state.loggedProteinToday.toFloat() / state.targetProteinGrams) * 100).toInt() else 0
                                    HomeScreen(
                                        caloricPercentage = calPerc.coerceIn(0, 100),
                                        currentKcal = state.loggedCaloriesToday,
                                        targetKcal = state.targetCalories,
                                        proteinPercentage = protPerc.coerceIn(0, 100),
                                        currentProtein = state.loggedProteinToday,
                                        targetProtein = state.targetProteinGrams,
                                        onStartWorkoutClick = {
                                            viewModel.selectTab(KayaTab.TRAIN)
                                        }
                                    )
                                }

                                KayaTab.TRAIN -> {
                                    TrainScreen(
                                        phonkTrackInfo = state.trackInfo,
                                        onTogglePlayPause = { viewModel.togglePlayPauseAudio() },
                                        onNextTrack = { viewModel.nextAudioTrack() },
                                        onPreviousTrack = { viewModel.previousAudioTrack() },
                                        onLogSetRecorded = { w, r ->
                                            viewModel.recordWorkoutSet(1L, "BENCH PRESS (MID CHEST)", w, r)
                                        },
                                        onFinishSessionClick = {
                                            viewModel.triggerSupabaseSync()
                                            viewModel.selectTab(KayaTab.STATS)
                                        }
                                    )
                                }

                                KayaTab.DIET -> {
                                    DietScreen(
                                        onAddMeal = { name, kcal, prot ->
                                            viewModel.recordLoggedMeal(name, kcal, prot)
                                        }
                                    )
                                }

                                KayaTab.STATS -> {
                                    StatsScreen(
                                        healthData = state.healthData,
                                        onSyncHealthConnectClick = {
                                            viewModel.triggerSupabaseSync()
                                        }
                                    )
                                }

                                KayaTab.MORE -> {
                                    MoreScreen(
                                        onSaveProfile = { w, c, p ->
                                            viewModel.saveUserProfile(w, c, p)
                                        },
                                        onManualSupabaseSyncClick = {
                                            viewModel.triggerSupabaseSync()
                                        }
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
