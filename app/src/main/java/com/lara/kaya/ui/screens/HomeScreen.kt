package com.lara.kaya.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lara.kaya.ui.components.MetricProgressBarCard
import com.lara.kaya.ui.components.MuscleRecoveryCard
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun HomeScreen(
    caloricPercentage: Int = 74,
    currentKcal: Int = 480,
    targetKcal: Int = 650,
    proteinPercentage: Int = 66,
    currentProtein: Int = 92,
    targetProtein: Int = 140,
    onStartWorkoutClick: () -> Unit
) {
    val textColor = MaterialTheme.colorScheme.onBackground
    val backgroundColor = MaterialTheme.colorScheme.background
    val currentDateStr = SimpleDateFormat("EEEE — MMM dd", Locale.US).format(Date()).uppercase(Locale.US)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        // Date Subtitle
        Text(
            text = currentDateStr,
            style = MaterialTheme.typography.labelSmall.copy(
                fontWeight = FontWeight.Bold,
                letterSpacing = 1.5.sp
            ),
            color = textColor.copy(alpha = 0.5f)
        )

        Spacer(modifier = Modifier.height(4.dp))

        // Greeting Header
        Text(
            text = "GOOD MORNING,\nATHLETE.",
            style = MaterialTheme.typography.headlineLarge.copy(
                fontSize = 28.sp,
                fontWeight = FontWeight.Black,
                lineHeight = 32.sp
            ),
            color = textColor
        )

        Spacer(modifier = Modifier.height(20.dp))

        // Caloric Expenditure Progress Card
        MetricProgressBarCard(
            title = "CALORIC EXPENDITURE",
            percentage = caloricPercentage,
            currentVal = currentKcal,
            targetVal = targetKcal,
            unit = "KCAL"
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Daily Protein Target Progress Card
        MetricProgressBarCard(
            title = "DAILY PROTEIN TARGET",
            percentage = proteinPercentage,
            currentVal = currentProtein,
            targetVal = targetProtein,
            unit = "G"
        )

        Spacer(modifier = Modifier.height(20.dp))

        // Muscle Recovery Status Header
        Text(
            text = "MUSCLE RECOVERY STATUS",
            style = MaterialTheme.typography.labelSmall.copy(
                fontWeight = FontWeight.Black,
                letterSpacing = 1.5.sp
            ),
            color = textColor.copy(alpha = 0.7f)
        )

        Spacer(modifier = Modifier.height(10.dp))

        // 2x2 Grid of Muscle Group Cards
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                MuscleRecoveryCard(
                    categoryTitle = "CHEST / DELTS",
                    badgeText = "READY TO TRAIN",
                    isRecovered = true,
                    modifier = Modifier.weight(1f)
                )
                MuscleRecoveryCard(
                    categoryTitle = "QUADS / GLUTES",
                    badgeText = "RECOVERING: 28H LEFT",
                    isRecovered = false,
                    modifier = Modifier.weight(1f)
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                MuscleRecoveryCard(
                    categoryTitle = "BACK / TRAPS",
                    badgeText = "READY TO TRAIN",
                    isRecovered = true,
                    modifier = Modifier.weight(1f)
                )
                MuscleRecoveryCard(
                    categoryTitle = "ARMS / CORE",
                    badgeText = "REC: 6H LEFT",
                    isRecovered = false,
                    modifier = Modifier.weight(1f)
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // START TODAY'S WORKOUT Button
        Button(
            onClick = onStartWorkoutClick,
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            shape = RoundedCornerShape(2.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = textColor,
                contentColor = backgroundColor
            )
        ) {
            Text(
                text = "START TODAY'S WORKOUT →",
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.Black,
                    letterSpacing = 1.5.sp
                )
            )
        }

        Spacer(modifier = Modifier.height(24.dp))
    }
}
