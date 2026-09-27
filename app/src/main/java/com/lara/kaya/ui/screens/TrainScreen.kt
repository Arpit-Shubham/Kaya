package com.lara.kaya.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lara.kaya.data.audio.TrackInfo
import kotlinx.coroutines.delay
import java.util.Locale

val MOTIVATIONAL_QUOTES = listOf(
    "— DISCIPLINE IS THE ONLY SHORTCUT. —",
    "— PAIN IS TEMPORARY. PRs ARE PERMANENT. —",
    "— NO EXCUSES. NO LIMITS. ONLY VOLUME. —",
    "— CONSISTENCY BEATS TALENT EVERY TIME. —",
    "— OBSESSION OUTFORCES HARD WORK. —"
)

@Composable
fun TrainScreen(
    currentExerciseName: String = "BENCH PRESS (MID CHEST)",
    currentSet: Int = 2,
    totalSets: Int = 4,
    phonkTrackInfo: TrackInfo = TrackInfo(),
    onTogglePlayPause: () -> Unit = {},
    onNextTrack: () -> Unit = {},
    onPreviousTrack: () -> Unit = {},
    onLogSetRecorded: (weightKg: Float, reps: Int) -> Unit = { _, _ -> },
    onFinishSessionClick: () -> Unit = {}
) {
    val textColor = MaterialTheme.colorScheme.onBackground
    val backgroundColor = MaterialTheme.colorScheme.background
    val surfaceColor = MaterialTheme.colorScheme.surface

    // Timer state in milliseconds
    var elapsedTimeMillis by remember { mutableLongStateOf(45124L) }
    var isTimerRunning by remember { mutableStateOf(false) }

    // Rotating Quote State
    var quoteIndex by remember { mutableIntStateOf(0) }

    // Log Set Modal State
    var showLogSetModal by remember { mutableStateOf(false) }
    var weightInput by remember { mutableStateOf("100") }
    var repsInput by remember { mutableStateOf("10") }

    // Stopwatch Coroutine
    LaunchedEffect(isTimerRunning) {
        if (isTimerRunning) {
            val startTime = System.currentTimeMillis() - elapsedTimeMillis
            while (isTimerRunning) {
                elapsedTimeMillis = System.currentTimeMillis() - startTime
                delay(50)
            }
        }
    }

    // Quote rotation every 15s
    LaunchedEffect(Unit) {
        while (true) {
            delay(15000)
            quoteIndex = (quoteIndex + 1) % MOTIVATIONAL_QUOTES.size
        }
    }

    // Timer Formatter: HH:MM:SS.T
    val seconds = (elapsedTimeMillis / 1000) % 60
    val minutes = (elapsedTimeMillis / (1000 * 60)) % 60
    val hours = (elapsedTimeMillis / (1000 * 60 * 60))
    val tenths = (elapsedTimeMillis % 1000) / 100
    val timeFormatted = String.format(Locale.US, "%02d:%02d:%02d", hours, minutes, seconds)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        // Subtitle Header
        Text(
            text = "ACTIVE SESSION",
            style = MaterialTheme.typography.labelSmall.copy(
                fontWeight = FontWeight.Bold,
                letterSpacing = 1.5.sp
            ),
            color = textColor.copy(alpha = 0.5f)
        )

        Spacer(modifier = Modifier.height(4.dp))

        // Exercise Name Header
        Text(
            text = currentExerciseName,
            style = MaterialTheme.typography.headlineLarge.copy(
                fontSize = 22.sp,
                fontWeight = FontWeight.Black
            ),
            color = textColor
        )

        Spacer(modifier = Modifier.height(2.dp))

        // Set Indicator Text
        Text(
            text = "— SET $currentSet / $totalSets",
            style = MaterialTheme.typography.titleMedium.copy(
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Monospace
            ),
            color = textColor.copy(alpha = 0.7f)
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Big Digital Stopwatch Timer Display
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { isTimerRunning = !isTimerRunning }
                .padding(vertical = 12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                verticalAlignment = Alignment.Bottom,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = timeFormatted,
                    style = MaterialTheme.typography.headlineLarge.copy(
                        fontSize = 48.sp,
                        fontWeight = FontWeight.Black,
                        fontFamily = FontFamily.Monospace,
                        letterSpacing = 2.sp
                    ),
                    color = textColor
                )
                Text(
                    text = ".$tenths",
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontSize = 28.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily.Monospace
                    ),
                    color = textColor.copy(alpha = 0.6f),
                    modifier = Modifier.padding(bottom = 6.dp)
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = if (isTimerRunning) "⏸ TAP TIMER TO PAUSE" else "◌ TAP TIMER TO START",
                style = MaterialTheme.typography.labelSmall.copy(
                    fontSize = 10.sp,
                    letterSpacing = 1.5.sp,
                    fontWeight = FontWeight.Bold
                ),
                color = textColor.copy(alpha = 0.6f)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Set Progress Indicator Lines (── ── ── ──)
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            (1..totalSets).forEach { index ->
                Box(
                    modifier = Modifier
                        .padding(horizontal = 4.dp)
                        .width(36.dp)
                        .height(6.dp)
                        .background(
                            if (index <= currentSet) textColor else textColor.copy(alpha = 0.2f)
                        )
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Motivational Quote Ticker
        Surface(
            modifier = Modifier.fillMaxWidth(),
            color = surfaceColor,
            border = BorderStroke(1.dp, textColor.copy(alpha = 0.15f))
        ) {
            Box(
                modifier = Modifier.padding(horizontal = 12.dp, vertical = 14.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = MOTIVATIONAL_QUOTES[quoteIndex],
                    style = MaterialTheme.typography.labelSmall.copy(
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Black,
                        letterSpacing = 1.sp
                    ),
                    color = textColor
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Phonk Local Audio Controller Card (Media3 ExoPlayer Wrapper)
        Surface(
            modifier = Modifier.fillMaxWidth(),
            color = surfaceColor,
            shape = RoundedCornerShape(2.dp),
            border = BorderStroke(1.dp, textColor.copy(alpha = 0.15f))
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                    modifier = Modifier.weight(1f)
                ) {
                    Icon(
                        imageVector = Icons.Default.MusicNote,
                        contentDescription = "Music",
                        tint = textColor,
                        modifier = Modifier.size(20.dp)
                    )
                    Column {
                        Text(
                            text = phonkTrackInfo.title,
                            style = MaterialTheme.typography.labelSmall.copy(
                                fontWeight = FontWeight.Black,
                                letterSpacing = 1.sp
                            ),
                            color = textColor
                        )
                        Text(
                            text = phonkTrackInfo.subtitle,
                            style = MaterialTheme.typography.labelSmall.copy(
                                fontSize = 9.sp,
                                fontFamily = FontFamily.Monospace
                            ),
                            color = textColor.copy(alpha = 0.5f)
                        )
                    }
                }

                // Audio Controls
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    IconButton(onClick = onPreviousTrack, modifier = Modifier.size(32.dp)) {
                        Icon(
                            imageVector = Icons.Default.SkipPrevious,
                            contentDescription = "Prev",
                            tint = textColor,
                            modifier = Modifier.size(16.dp)
                        )
                    }

                    Box(
                        modifier = Modifier
                            .size(36.dp)
                            .border(1.dp, textColor)
                            .clickable { onTogglePlayPause() },
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = if (phonkTrackInfo.isPlaying) Icons.Default.Pause else Icons.Default.PlayArrow,
                            contentDescription = "Play/Pause",
                            tint = textColor,
                            modifier = Modifier.size(18.dp)
                        )
                    }

                    IconButton(onClick = onNextTrack, modifier = Modifier.size(32.dp)) {
                        Icon(
                            imageVector = Icons.Default.SkipNext,
                            contentDescription = "Next",
                            tint = textColor,
                            modifier = Modifier.size(16.dp)
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(28.dp))

        // Bottom CTA Action Buttons: LOG SET & FINISH
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            // LOG SET (Solid White Button)
            Button(
                onClick = { showLogSetModal = true },
                modifier = Modifier
                    .weight(1f)
                    .height(52.dp),
                shape = RoundedCornerShape(2.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = textColor,
                    contentColor = backgroundColor
                )
            ) {
                Text(
                    text = "LOG SET",
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.Black,
                        letterSpacing = 1.sp
                    )
                )
            }

            // FINISH (Outlined Button)
            OutlinedButton(
                onClick = onFinishSessionClick,
                modifier = Modifier
                    .weight(1f)
                    .height(52.dp),
                shape = RoundedCornerShape(2.dp),
                border = BorderStroke(1.dp, textColor),
                colors = ButtonDefaults.outlinedButtonColors(
                    contentColor = textColor
                )
            ) {
                Text(
                    text = "FINISH",
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.Black,
                        letterSpacing = 1.sp
                    )
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))
    }

    // LOG SET Modal Dialog
    if (showLogSetModal) {
        AlertDialog(
            onDismissRequest = { showLogSetModal = false },
            containerColor = surfaceColor,
            title = {
                Text(
                    text = "LOG COMPLETED SET",
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Black),
                    color = textColor
                )
            },
            text = {
                Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    OutlinedTextField(
                        value = weightInput,
                        onValueChange = { weightInput = it },
                        label = { Text("LOAD (KG)") },
                        singleLine = true,
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = textColor,
                            unfocusedBorderColor = textColor.copy(alpha = 0.5f),
                            focusedLabelColor = textColor,
                            unfocusedLabelColor = textColor.copy(alpha = 0.5f)
                        )
                    )

                    OutlinedTextField(
                        value = repsInput,
                        onValueChange = { repsInput = it },
                        label = { Text("REPETITIONS") },
                        singleLine = true,
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = textColor,
                            unfocusedBorderColor = textColor.copy(alpha = 0.5f),
                            focusedLabelColor = textColor,
                            unfocusedLabelColor = textColor.copy(alpha = 0.5f)
                        )
                    )
                }
            },
            confirmButton = {
                Button(
                    onClick = {
                        val weight = weightInput.toFloatOrNull() ?: 100f
                        val reps = repsInput.toIntOrNull() ?: 10
                        onLogSetRecorded(weight, reps)
                        showLogSetModal = false
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = textColor,
                        contentColor = backgroundColor
                    )
                ) {
                    Text("SAVE SET")
                }
            },
            dismissButton = {
                TextButton(onClick = { showLogSetModal = false }) {
                    Text("CANCEL", color = textColor)
                }
            }
        )
    }
}
