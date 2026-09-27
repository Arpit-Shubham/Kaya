package com.lara.kaya.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.util.Locale

@Composable
fun KayaTopBar(
    streakDays: Int,
    isInverted: Boolean,
    onToggleInvert: () -> Unit
) {
    val textColor = MaterialTheme.colorScheme.onBackground
    val surfaceColor = MaterialTheme.colorScheme.surface

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .statusBarsPadding()
            .padding(horizontal = 16.dp, vertical = 12.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        // App Title Logo
        Text(
            text = "K A Y A",
            style = MaterialTheme.typography.titleLarge.copy(
                fontWeight = FontWeight.Black,
                letterSpacing = 4.sp
            ),
            color = textColor
        )

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            // Streak Badge
            Surface(
                color = surfaceColor,
                shape = RoundedCornerShape(16.dp),
                border = BorderStroke(1.dp, textColor.copy(alpha = 0.5f))
            ) {
                Row(
                    modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    Text(text = "🔥", fontSize = 12.sp)
                    Text(
                        text = "$streakDays DAYS",
                        style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold),
                        color = textColor
                    )
                }
            }

            // Theme Invert Toggle
            IconButton(
                onClick = onToggleInvert,
                modifier = Modifier
                    .size(36.dp)
                    .background(surfaceColor, shape = RoundedCornerShape(18.dp))
                    .border(1.dp, textColor.copy(alpha = 0.5f), shape = RoundedCornerShape(18.dp))
            ) {
                Text(
                    text = if (isInverted) "☀️" else "🌓",
                    fontSize = 14.sp
                )
            }
        }
    }
}

@Composable
fun MetricProgressBarCard(
    title: String,
    percentage: Int,
    currentVal: Int,
    targetVal: Int,
    unit: String
) {
    val textColor = MaterialTheme.colorScheme.onBackground
    val surfaceColor = MaterialTheme.colorScheme.surface
    val progressFillColor = MaterialTheme.colorScheme.onBackground

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp),
        color = surfaceColor,
        shape = RoundedCornerShape(2.dp),
        border = BorderStroke(1.dp, textColor.copy(alpha = 0.15f))
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = title.uppercase(Locale.getDefault()),
                    style = MaterialTheme.typography.labelSmall.copy(
                        letterSpacing = 1.5.sp,
                        fontWeight = FontWeight.Bold
                    ),
                    color = textColor.copy(alpha = 0.7f)
                )
                Text(
                    text = "$percentage%",
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontWeight = FontWeight.Black,
                        fontFamily = FontFamily.Monospace
                    ),
                    color = textColor
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Progress Bar
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(8.dp)
                    .background(textColor.copy(alpha = 0.15f))
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxHeight()
                        .fillMaxWidth(fraction = (percentage / 100f).coerceIn(0f, 1f))
                        .background(progressFillColor)
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = "$currentVal / $targetVal $unit",
                style = MaterialTheme.typography.labelSmall.copy(
                    fontFamily = FontFamily.Monospace,
                    fontWeight = FontWeight.Bold
                ),
                color = textColor.copy(alpha = 0.8f)
            )
        }
    }
}

@Composable
fun MuscleRecoveryCard(
    categoryTitle: String,
    badgeText: String,
    isRecovered: Boolean,
    modifier: Modifier = Modifier
) {
    val textColor = MaterialTheme.colorScheme.onBackground
    val surfaceColor = MaterialTheme.colorScheme.surface

    Surface(
        modifier = modifier,
        color = surfaceColor,
        shape = RoundedCornerShape(2.dp),
        border = BorderStroke(1.dp, textColor.copy(alpha = 0.15f))
    ) {
        Column(
            modifier = Modifier.padding(12.dp)
        ) {
            Text(
                text = categoryTitle.uppercase(Locale.getDefault()),
                style = MaterialTheme.typography.labelSmall.copy(
                    fontWeight = FontWeight.Black,
                    letterSpacing = 1.sp
                ),
                color = textColor
            )

            Spacer(modifier = Modifier.height(10.dp))

            // Badge
            if (isRecovered) {
                Surface(
                    color = Color.Transparent,
                    shape = RoundedCornerShape(0.dp),
                    border = BorderStroke(1.dp, textColor)
                ) {
                    Text(
                        text = badgeText,
                        style = MaterialTheme.typography.labelSmall.copy(
                            fontSize = 9.sp,
                            fontWeight = FontWeight.Bold
                        ),
                        color = textColor,
                        modifier = Modifier.padding(horizontal = 6.dp, vertical = 4.dp)
                    )
                }
            } else {
                // Dashed border badge
                Box(
                    modifier = Modifier
                        .dashedBorder(1.dp, textColor.copy(alpha = 0.7f), 4.dp)
                        .padding(horizontal = 6.dp, vertical = 4.dp)
                ) {
                    Text(
                        text = badgeText,
                        style = MaterialTheme.typography.labelSmall.copy(
                            fontSize = 9.sp,
                            fontWeight = FontWeight.Bold
                        ),
                        color = textColor.copy(alpha = 0.7f)
                    )
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                Text(
                    text = if (isRecovered) "●" else "◌",
                    fontSize = 10.sp,
                    color = textColor
                )
                Text(
                    text = if (isRecovered) "RECOVERED" else "RECOVERING",
                    style = MaterialTheme.typography.labelSmall.copy(fontSize = 9.sp),
                    color = textColor.copy(alpha = 0.7f)
                )
            }
        }
    }
}

// Extension modifier for dashed border
fun Modifier.dashedBorder(width: Dp, color: Color, dashLength: Dp = 4.dp) = this.drawWithContent {
    drawContent()
    val strokeWidth = width.toPx()
    val dashPx = dashLength.toPx()
    val pathEffect = PathEffect.dashPathEffect(floatArrayOf(dashPx, dashPx), 0f)
    drawRect(
        color = color,
        style = Stroke(width = strokeWidth, pathEffect = pathEffect)
    )
}

@Composable
fun MonochromaticHatchedBarChart(
    weeklyData: List<Pair<String, Pair<Float, Boolean>>> // Day, (VolumeKg, isActive)
) {
    val textColor = MaterialTheme.colorScheme.onBackground
    val surfaceColor = MaterialTheme.colorScheme.surface
    val maxVolume = (weeklyData.maxOfOrNull { it.second.first } ?: 15000f).coerceAtLeast(1000f)

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(surfaceColor, RoundedCornerShape(2.dp))
            .border(1.dp, textColor.copy(alpha = 0.15f))
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "WEEKLY TRAINING VOLUME",
                style = MaterialTheme.typography.labelSmall.copy(
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 1.5.sp
                ),
                color = textColor.copy(alpha = 0.7f)
            )
            Text(
                text = "WEEK 38  +12% ↑",
                style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold),
                color = textColor
            )
        }

        Spacer(modifier = Modifier.height(6.dp))

        Text(
            text = "54,200 KG",
            style = MaterialTheme.typography.headlineLarge.copy(
                fontWeight = FontWeight.Black,
                fontFamily = FontFamily.Monospace
            ),
            color = textColor
        )

        Spacer(modifier = Modifier.height(20.dp))

        // Chart Area
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(130.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.Bottom
        ) {
            weeklyData.forEach { (day, data) ->
                val (vol, isActive) = data
                val fraction = (vol / maxVolume).coerceIn(0.08f, 1f)
                val label = if (vol > 0) String.format(Locale.US, "%.1fK", vol / 1000f) else ""

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxHeight(),
                    verticalArrangement = Arrangement.Bottom
                ) {
                    if (label.isNotEmpty()) {
                        Text(
                            text = label,
                            style = MaterialTheme.typography.labelSmall.copy(
                                fontSize = 8.sp,
                                fontFamily = FontFamily.Monospace
                            ),
                            color = textColor.copy(alpha = 0.8f)
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                    }

                    if (isActive) {
                        // Hatched Bar
                        Canvas(
                            modifier = Modifier
                                .width(28.dp)
                                .fillMaxHeight(fraction)
                                .border(1.dp, textColor)
                        ) {
                            val strokeWidth = 1.5.dp.toPx()
                            val spacing = 8.dp.toPx()
                            var x = -size.height
                            while (x < size.width + size.height) {
                                drawLine(
                                    color = textColor,
                                    start = Offset(x, size.height),
                                    end = Offset(x + size.height, 0f),
                                    strokeWidth = strokeWidth
                                )
                                x += spacing
                            }
                        }
                    } else {
                        // Dashed Rest Day Bar Outline
                        Box(
                            modifier = Modifier
                                .width(28.dp)
                                .fillMaxHeight(0.7f)
                                .dashedBorder(1.dp, textColor.copy(alpha = 0.3f), 4.dp)
                        )
                    }

                    Spacer(modifier = Modifier.height(6.dp))

                    Text(
                        text = day,
                        style = MaterialTheme.typography.labelSmall.copy(
                            fontSize = 9.sp,
                            fontWeight = FontWeight.Bold
                        ),
                        color = textColor.copy(alpha = 0.7f)
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
        HorizontalDivider(color = textColor.copy(alpha = 0.15f))
        Spacer(modifier = Modifier.height(12.dp))

        // Legend
        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                Box(
                    modifier = Modifier
                        .size(12.dp)
                        .border(1.dp, textColor)
                ) {
                    Canvas(modifier = Modifier.fillMaxSize()) {
                        drawLine(
                            color = textColor,
                            start = Offset(0f, size.height),
                            end = Offset(size.width, 0f),
                            strokeWidth = 1.dp.toPx()
                        )
                    }
                }
                Text(
                    text = "ACTIVE DAY",
                    style = MaterialTheme.typography.labelSmall.copy(fontSize = 9.sp),
                    color = textColor.copy(alpha = 0.8f)
                )
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                Box(
                    modifier = Modifier
                        .size(12.dp)
                        .dashedBorder(1.dp, textColor.copy(alpha = 0.5f), 2.dp)
                )
                Text(
                    text = "REST DAY",
                    style = MaterialTheme.typography.labelSmall.copy(fontSize = 9.sp),
                    color = textColor.copy(alpha = 0.8f)
                )
            }
        }
    }
}

enum class KayaTab(val title: String, val icon: ImageVector) {
    HOME("HOME", Icons.Default.Home),
    TRAIN("TRAIN", Icons.Default.FitnessCenter),
    DIET("DIET", Icons.Default.PieChart),
    STATS("STATS", Icons.Default.BarChart),
    MORE("MORE", Icons.Default.MoreHoriz)
}

@Composable
fun KayaBottomNavigation(
    currentTab: KayaTab,
    onTabSelected: (KayaTab) -> Unit
) {
    val backgroundColor = MaterialTheme.colorScheme.background
    val textColor = MaterialTheme.colorScheme.onBackground

    Surface(
        color = backgroundColor,
        border = BorderStroke(1.dp, textColor.copy(alpha = 0.15f))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .navigationBarsPadding()
                .height(60.dp),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            KayaTab.entries.forEach { tab ->
                val isSelected = currentTab == tab

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxHeight()
                        .weight(1f)
                        .clickable { onTabSelected(tab) }
                ) {
                    // Top active bar indicator
                    if (isSelected) {
                        Box(
                            modifier = Modifier
                                .width(24.dp)
                                .height(2.dp)
                                .background(textColor)
                        )
                        Spacer(modifier = Modifier.height(6.dp))
                    } else {
                        Spacer(modifier = Modifier.height(8.dp))
                    }

                    Icon(
                        imageVector = tab.icon,
                        contentDescription = tab.title,
                        tint = if (isSelected) textColor else textColor.copy(alpha = 0.4f),
                        modifier = Modifier.size(20.dp)
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    Text(
                        text = tab.title,
                        style = MaterialTheme.typography.labelSmall.copy(
                            fontSize = 9.sp,
                            fontWeight = if (isSelected) FontWeight.Black else FontWeight.Normal
                        ),
                        color = if (isSelected) textColor else textColor.copy(alpha = 0.4f)
                    )
                }
            }
        }
    }
}
