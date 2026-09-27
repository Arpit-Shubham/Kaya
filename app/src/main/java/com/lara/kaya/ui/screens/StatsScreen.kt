package com.lara.kaya.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lara.kaya.data.health.HealthData
import com.lara.kaya.ui.components.MonochromaticHatchedBarChart
import java.util.Locale

data class PersonalRecord(
    val exerciseName: String,
    val gainLabel: String,
    val recordKg: Int
)

@Composable
fun StatsScreen(
    healthData: HealthData = HealthData(),
    onSyncHealthConnectClick: () -> Unit = {}
) {
    val textColor = MaterialTheme.colorScheme.onBackground
    val backgroundColor = MaterialTheme.colorScheme.background
    val surfaceColor = MaterialTheme.colorScheme.surface

    val weeklyVolumeData = listOf(
        "MON" to Pair(12400f, true),
        "TUE" to Pair(8200f, true),
        "WED" to Pair(0f, false),
        "THU" to Pair(14800f, true),
        "FRI" to Pair(11200f, true),
        "SAT" to Pair(6800f, true),
        "SUN" to Pair(0f, false)
    )

    val personalRecords = listOf(
        PersonalRecord("BENCH PRESS", "+5KG", 100),
        PersonalRecord("DEADLIFT", "+10KG", 160),
        PersonalRecord("SQUAT", "+5KG", 140)
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        // Subtitle Header
        Text(
            text = "PERFORMANCE",
            style = MaterialTheme.typography.labelSmall.copy(
                fontWeight = FontWeight.Bold,
                letterSpacing = 1.5.sp
            ),
            color = textColor.copy(alpha = 0.5f)
        )

        Spacer(modifier = Modifier.height(4.dp))

        // Main Header
        Text(
            text = "ANALYTICS",
            style = MaterialTheme.typography.headlineLarge.copy(
                fontSize = 28.sp,
                fontWeight = FontWeight.Black
            ),
            color = textColor
        )

        Spacer(modifier = Modifier.height(20.dp))

        // Monochromatic Hatched Weekly Volume Chart
        MonochromaticHatchedBarChart(weeklyData = weeklyVolumeData)

        Spacer(modifier = Modifier.height(16.dp))

        // Health Connect Live Sync Card
        Surface(
            modifier = Modifier.fillMaxWidth(),
            color = surfaceColor,
            shape = RoundedCornerShape(2.dp),
            border = BorderStroke(1.dp, textColor.copy(alpha = 0.15f))
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "HEALTH CONNECT",
                        style = MaterialTheme.typography.labelSmall.copy(
                            fontWeight = FontWeight.Bold,
                            letterSpacing = 1.5.sp
                        ),
                        color = textColor.copy(alpha = 0.7f)
                    )

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Text(
                            text = if (healthData.isLive) "● LIVE" else "◌ OFF",
                            style = MaterialTheme.typography.labelSmall.copy(
                                fontSize = 9.sp,
                                fontWeight = FontWeight.Bold
                            ),
                            color = textColor
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    // Steps Today Box
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = "STEPS TODAY",
                            style = MaterialTheme.typography.labelSmall.copy(
                                fontSize = 9.sp,
                                fontWeight = FontWeight.Bold,
                                letterSpacing = 1.sp
                            ),
                            color = textColor.copy(alpha = 0.6f)
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = String.format(Locale.US, "%,d", healthData.stepsToday),
                            style = MaterialTheme.typography.headlineLarge.copy(
                                fontSize = 32.sp,
                                fontWeight = FontWeight.Black,
                                fontFamily = FontFamily.Monospace
                            ),
                            color = textColor
                        )
                        Spacer(modifier = Modifier.height(6.dp))
                        // Progress Bar
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(4.dp)
                                .background(textColor.copy(alpha = 0.15f))
                        ) {
                            Box(
                                modifier = Modifier
                                    .fillMaxHeight()
                                    .fillMaxWidth((healthData.stepsToday.toFloat() / healthData.stepGoal).coerceIn(0f, 1f))
                                    .background(textColor)
                            )
                        }
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = "${((healthData.stepsToday.toFloat() / healthData.stepGoal) * 100).toInt()}% OF 10K GOAL",
                            style = MaterialTheme.typography.labelSmall.copy(
                                fontSize = 8.sp,
                                fontFamily = FontFamily.Monospace
                            ),
                            color = textColor.copy(alpha = 0.5f)
                        )
                    }

                    // Active Kcal Box
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = "ACTIVE KCAL",
                            style = MaterialTheme.typography.labelSmall.copy(
                                fontSize = 9.sp,
                                fontWeight = FontWeight.Bold,
                                letterSpacing = 1.sp
                            ),
                            color = textColor.copy(alpha = 0.6f)
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = "${healthData.activeKcalToday}",
                            style = MaterialTheme.typography.headlineLarge.copy(
                                fontSize = 32.sp,
                                fontWeight = FontWeight.Black,
                                fontFamily = FontFamily.Monospace
                            ),
                            color = textColor
                        )
                        Spacer(modifier = Modifier.height(6.dp))
                        // Progress Bar
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(4.dp)
                                .background(textColor.copy(alpha = 0.15f))
                        ) {
                            Box(
                                modifier = Modifier
                                    .fillMaxHeight()
                                    .fillMaxWidth((healthData.activeKcalToday.toFloat() / healthData.kcalGoal).coerceIn(0f, 1f))
                                    .background(textColor)
                            )
                        }
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = "${((healthData.activeKcalToday.toFloat() / healthData.kcalGoal) * 100).toInt()}% OF 650 GOAL",
                            style = MaterialTheme.typography.labelSmall.copy(
                                fontSize = 8.sp,
                                fontFamily = FontFamily.Monospace
                            ),
                            color = textColor.copy(alpha = 0.5f)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))
                HorizontalDivider(color = textColor.copy(alpha = 0.15f))
                Spacer(modifier = Modifier.height(12.dp))

                // Sync status timestamp row
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = healthData.lastSyncFormatted,
                        style = MaterialTheme.typography.labelSmall.copy(
                            fontSize = 9.sp,
                            fontFamily = FontFamily.Monospace
                        ),
                        color = textColor.copy(alpha = 0.6f)
                    )

                    IconButton(
                        onClick = onSyncHealthConnectClick,
                        modifier = Modifier.size(24.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Refresh,
                            contentDescription = "Refresh Health Connect",
                            tint = textColor,
                            modifier = Modifier.size(16.dp)
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Personal Records Card (1RM)
        Surface(
            modifier = Modifier.fillMaxWidth(),
            color = surfaceColor,
            shape = RoundedCornerShape(2.dp),
            border = BorderStroke(1.dp, textColor.copy(alpha = 0.15f))
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = "PERSONAL RECORDS — 1RM",
                    style = MaterialTheme.typography.labelSmall.copy(
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 1.5.sp
                    ),
                    color = textColor.copy(alpha = 0.7f)
                )

                Spacer(modifier = Modifier.height(16.dp))

                personalRecords.forEachIndexed { index, pr ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = pr.exerciseName,
                            style = MaterialTheme.typography.titleMedium.copy(
                                fontWeight = FontWeight.Black,
                                letterSpacing = 1.sp
                            ),
                            color = textColor
                        )

                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Surface(
                                color = textColor.copy(alpha = 0.08f),
                                border = BorderStroke(1.dp, textColor.copy(alpha = 0.3f))
                            ) {
                                Text(
                                    text = pr.gainLabel,
                                    style = MaterialTheme.typography.labelSmall.copy(
                                        fontSize = 8.sp,
                                        fontFamily = FontFamily.Monospace,
                                        fontWeight = FontWeight.Bold
                                    ),
                                    color = textColor,
                                    modifier = Modifier.padding(horizontal = 4.dp, vertical = 2.dp)
                                )
                            }

                            Text(
                                text = "${pr.recordKg} KG",
                                style = MaterialTheme.typography.titleMedium.copy(
                                    fontWeight = FontWeight.Black,
                                    fontFamily = FontFamily.Monospace
                                ),
                                color = textColor
                            )
                        }
                    }

                    if (index < personalRecords.size - 1) {
                        HorizontalDivider(color = textColor.copy(alpha = 0.1f))
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))
    }
}
