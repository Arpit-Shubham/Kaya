package com.lara.kaya.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CloudSync
import androidx.compose.material.icons.filled.FitnessCenter
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lara.kaya.data.local.PreseedData
import com.lara.kaya.data.local.entity.ExerciseEntity
import com.lara.kaya.data.local.entity.UserProfileEntity

@Composable
fun MoreScreen(
    userProfile: UserProfileEntity = UserProfileEntity(),
    exercises: List<ExerciseEntity> = PreseedData.exercises,
    onSaveProfile: (weightKg: Float, calorieGoal: Int, proteinGoal: Int) -> Unit = { _, _, _ -> },
    onManualSupabaseSyncClick: () -> Unit = {}
) {
    val textColor = MaterialTheme.colorScheme.onBackground
    val backgroundColor = MaterialTheme.colorScheme.background
    val surfaceColor = MaterialTheme.colorScheme.surface

    var weightInput by remember { mutableStateOf(userProfile.weightKg.toString()) }
    var calorieGoalInput by remember { mutableStateOf(userProfile.targetCalories.toString()) }
    var proteinGoalInput by remember { mutableStateOf(userProfile.targetProteinGrams.toString()) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        // Subtitle Header
        Text(
            text = "SYSTEM & CATALOGS",
            style = MaterialTheme.typography.labelSmall.copy(
                fontWeight = FontWeight.Bold,
                letterSpacing = 1.5.sp
            ),
            color = textColor.copy(alpha = 0.5f)
        )

        Spacer(modifier = Modifier.height(4.dp))

        // Main Header
        Text(
            text = "MORE SETTINGS",
            style = MaterialTheme.typography.headlineLarge.copy(
                fontSize = 28.sp,
                fontWeight = FontWeight.Black
            ),
            color = textColor
        )

        Spacer(modifier = Modifier.height(20.dp))

        // User Profile Card
        Surface(
            modifier = Modifier.fillMaxWidth(),
            color = surfaceColor,
            shape = RoundedCornerShape(2.dp),
            border = BorderStroke(1.dp, textColor.copy(alpha = 0.15f))
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Icon(imageVector = Icons.Default.Person, contentDescription = "Profile", tint = textColor)
                    Text(
                        text = "ATHLETE PROFILE & GOALS",
                        style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Black),
                        color = textColor
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    OutlinedTextField(
                        value = weightInput,
                        onValueChange = { weightInput = it },
                        label = { Text("WEIGHT (KG)") },
                        modifier = Modifier.weight(1f),
                        singleLine = true,
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = textColor,
                            unfocusedBorderColor = textColor.copy(alpha = 0.3f),
                            focusedLabelColor = textColor
                        )
                    )

                    OutlinedTextField(
                        value = calorieGoalInput,
                        onValueChange = { calorieGoalInput = it },
                        label = { Text("KCAL GOAL") },
                        modifier = Modifier.weight(1f),
                        singleLine = true,
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = textColor,
                            unfocusedBorderColor = textColor.copy(alpha = 0.3f),
                            focusedLabelColor = textColor
                        )
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = proteinGoalInput,
                    onValueChange = { proteinGoalInput = it },
                    label = { Text("PROTEIN GOAL (G)") },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = textColor,
                        unfocusedBorderColor = textColor.copy(alpha = 0.3f),
                        focusedLabelColor = textColor
                    )
                )

                Spacer(modifier = Modifier.height(14.dp))

                Button(
                    onClick = {
                        val w = weightInput.toFloatOrNull() ?: 75f
                        val c = calorieGoalInput.toIntOrNull() ?: 650
                        val p = proteinGoalInput.toIntOrNull() ?: 140
                        onSaveProfile(w, c, p)
                    },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(2.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = textColor,
                        contentColor = backgroundColor
                    )
                ) {
                    Text("SAVE PROFILE CHANGES")
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Supabase Free Tier Sync Card
        Surface(
            modifier = Modifier.fillMaxWidth(),
            color = surfaceColor,
            shape = RoundedCornerShape(2.dp),
            border = BorderStroke(1.dp, textColor.copy(alpha = 0.15f))
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Icon(imageVector = Icons.Default.CloudSync, contentDescription = "Sync", tint = textColor)
                    Text(
                        text = "SUPABASE FREE TIER SYNC",
                        style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Black),
                        color = textColor
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "All raw workout logs and meal catalogs remain on local Room DB (500MB free-tier optimization). Only daily summary aggregates sync to Supabase online once per day.",
                    style = MaterialTheme.typography.bodyMedium,
                    color = textColor.copy(alpha = 0.7f)
                )

                Spacer(modifier = Modifier.height(14.dp))

                OutlinedButton(
                    onClick = onManualSupabaseSyncClick,
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(2.dp),
                    border = BorderStroke(1.dp, textColor),
                    colors = ButtonDefaults.outlinedButtonColors(contentColor = textColor)
                ) {
                    Text("TRIGGER MANUAL SUPABASE SYNC")
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Pre-seeded Exercise Catalog Browser
        Surface(
            modifier = Modifier.fillMaxWidth(),
            color = surfaceColor,
            shape = RoundedCornerShape(2.dp),
            border = BorderStroke(1.dp, textColor.copy(alpha = 0.15f))
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Icon(imageVector = Icons.Default.FitnessCenter, contentDescription = "Exercises", tint = textColor)
                    Text(
                        text = "PRE-SEEDED EXERCISE CATALOG (${exercises.size})",
                        style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Black),
                        color = textColor
                    )
                }

                Spacer(modifier = Modifier.height(12.dp))

                Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                    exercises.take(15).forEach { ex ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .border(1.dp, textColor.copy(alpha = 0.1f))
                                .padding(10.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column {
                                Text(
                                    text = ex.name,
                                    style = MaterialTheme.typography.titleMedium.copy(fontSize = 13.sp, fontWeight = FontWeight.Bold),
                                    color = textColor
                                )
                                Text(
                                    text = "${ex.muscleGroup.displayName} · MET: ${ex.defaultMET} · Rec: ${ex.recoveryHours}h",
                                    style = MaterialTheme.typography.labelSmall.copy(fontSize = 9.sp, fontFamily = FontFamily.Monospace),
                                    color = textColor.copy(alpha = 0.5f)
                                )
                            }
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))
    }
}
