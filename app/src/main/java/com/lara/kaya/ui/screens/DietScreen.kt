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
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lara.kaya.data.local.PreseedData
import com.lara.kaya.data.local.entity.IngredientEntity
import com.lara.kaya.data.local.entity.QuickMealEntity
import com.lara.kaya.domain.Calculations
import java.util.Locale

@Composable
fun DietScreen(
    quickMeals: List<QuickMealEntity> = PreseedData.quickMeals,
    allIngredients: List<IngredientEntity> = PreseedData.ingredients,
    onAddMeal: (name: String, calories: Int, proteinGrams: Float) -> Unit = { _, _, _ -> }
) {
    val textColor = MaterialTheme.colorScheme.onBackground
    val backgroundColor = MaterialTheme.colorScheme.background
    val surfaceColor = MaterialTheme.colorScheme.surface

    var selectedTabIndex by remember { mutableIntStateOf(0) }
    val snackbarHostState = remember { SnackbarHostState() }

    // Custom Recipe State
    val customMealName by remember { mutableStateOf("MY ATHLETE BOWL") }
    val selectedIngredientGrams = remember { mutableStateMapOf<IngredientEntity, Float>() }

    val totalDishKcal = Calculations.calculateDishCalories(selectedIngredientGrams).toInt()
    val totalDishProtein = Calculations.calculateDishProtein(selectedIngredientGrams)

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        containerColor = backgroundColor
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            // Subtitle Header
            Text(
                text = "NUTRITION LOG",
                style = MaterialTheme.typography.labelSmall.copy(
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 1.5.sp
                ),
                color = textColor.copy(alpha = 0.5f)
            )

            Spacer(modifier = Modifier.height(4.dp))

            // Main Header
            Text(
                text = "MEAL COMPOSER",
                style = MaterialTheme.typography.headlineLarge.copy(
                    fontSize = 26.sp,
                    fontWeight = FontWeight.Black
                ),
                color = textColor
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Tab Selector: [ 10 QUICK MEALS ] and [ CUSTOM RECIPE ]
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .border(1.dp, textColor.copy(alpha = 0.15f)),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                // Tab 0
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .background(if (selectedTabIndex == 0) textColor else backgroundColor)
                        .clickable { selectedTabIndex = 0 }
                        .padding(vertical = 12.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "[ 10 QUICK MEALS ]",
                        style = MaterialTheme.typography.labelSmall.copy(
                            fontWeight = FontWeight.Black,
                            letterSpacing = 1.sp
                        ),
                        color = if (selectedTabIndex == 0) backgroundColor else textColor
                    )
                }

                // Tab 1
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .background(if (selectedTabIndex == 1) textColor else backgroundColor)
                        .clickable { selectedTabIndex = 1 }
                        .padding(vertical = 12.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "[ CUSTOM RECIPE ]",
                        style = MaterialTheme.typography.labelSmall.copy(
                            fontWeight = FontWeight.Black,
                            letterSpacing = 1.sp
                        ),
                        color = if (selectedTabIndex == 1) backgroundColor else textColor
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            if (selectedTabIndex == 0) {
                // 10 Quick Meals List
                Column(
                    verticalArrangement = Arrangement.spacedBy(10.dp),
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState())
                ) {
                    quickMeals.forEach { meal ->
                        Surface(
                            modifier = Modifier.fillMaxWidth(),
                            color = surfaceColor,
                            shape = RoundedCornerShape(2.dp),
                            border = BorderStroke(1.dp, textColor.copy(alpha = 0.15f))
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(14.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                                    modifier = Modifier.weight(1f)
                                ) {
                                    Text(text = meal.emojiIcon, fontSize = 24.sp)

                                    Column {
                                        Text(
                                            text = meal.name,
                                            style = MaterialTheme.typography.titleMedium.copy(
                                                fontWeight = FontWeight.Black,
                                                letterSpacing = 0.5.sp
                                            ),
                                            color = textColor
                                        )

                                        Spacer(modifier = Modifier.height(6.dp))

                                        Row(
                                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                                            verticalAlignment = Alignment.CenterVertically
                                        ) {
                                            // KCAL Badge
                                            Surface(
                                                color = textColor.copy(alpha = 0.08f),
                                                border = BorderStroke(1.dp, textColor.copy(alpha = 0.2f))
                                            ) {
                                                Text(
                                                    text = "${meal.kcal} KCAL",
                                                    style = MaterialTheme.typography.labelSmall.copy(
                                                        fontSize = 9.sp,
                                                        fontFamily = FontFamily.Monospace,
                                                        fontWeight = FontWeight.Bold
                                                    ),
                                                    color = textColor,
                                                    modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                                                )
                                            }

                                            // PROTEIN Badge
                                            Surface(
                                                color = textColor.copy(alpha = 0.08f),
                                                border = BorderStroke(1.dp, textColor)
                                            ) {
                                                Text(
                                                    text = "${meal.proteinGrams.toInt()}G PROTEIN",
                                                    style = MaterialTheme.typography.labelSmall.copy(
                                                        fontSize = 9.sp,
                                                        fontFamily = FontFamily.Monospace,
                                                        fontWeight = FontWeight.Bold
                                                    ),
                                                    color = textColor,
                                                    modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                                                )
                                            }
                                        }
                                    }
                                }

                                // Quick Add Button (+)
                                Box(
                                    modifier = Modifier
                                        .size(36.dp)
                                        .border(1.dp, textColor)
                                        .clickable {
                                            onAddMeal(meal.name, meal.kcal, meal.proteinGrams)
                                        },
                                    contentAlignment = Alignment.Center
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.Add,
                                        contentDescription = "Add Meal",
                                        tint = textColor,
                                        modifier = Modifier.size(18.dp)
                                    )
                                }
                            }
                        }
                    }
                }
            } else {
                // Custom Recipe Builder Tab
                Column(
                    modifier = Modifier.fillMaxSize()
                ) {
                    // Total Aggregated Nutrition Display Header
                    Surface(
                        modifier = Modifier.fillMaxWidth(),
                        color = surfaceColor,
                        border = BorderStroke(1.dp, textColor)
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column {
                                Text(
                                    text = "TOTAL DISH NUTRITION",
                                    style = MaterialTheme.typography.labelSmall.copy(
                                        fontWeight = FontWeight.Bold,
                                        letterSpacing = 1.sp
                                    ),
                                    color = textColor.copy(alpha = 0.7f)
                                )
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(
                                    text = "$totalDishKcal KCAL  ·  ${String.format(Locale.US, "%.1f", totalDishProtein)}G PROTEIN",
                                    style = MaterialTheme.typography.titleMedium.copy(
                                        fontWeight = FontWeight.Black,
                                        fontFamily = FontFamily.Monospace
                                    ),
                                    color = textColor
                                )
                            }

                            Button(
                                onClick = {
                                    if (selectedIngredientGrams.isNotEmpty()) {
                                        onAddMeal(customMealName, totalDishKcal, totalDishProtein)
                                        selectedIngredientGrams.clear()
                                    }
                                },
                                shape = RoundedCornerShape(2.dp),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = textColor,
                                    contentColor = backgroundColor
                                )
                            ) {
                                Text(
                                    text = "LOG DISH",
                                    style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Black)
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(14.dp))

                    Text(
                        text = "SELECT INGREDIENTS & GRAMS (PER 100G BASE)",
                        style = MaterialTheme.typography.labelSmall.copy(
                            fontWeight = FontWeight.Bold,
                            letterSpacing = 1.sp
                        ),
                        color = textColor.copy(alpha = 0.6f)
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    // Ingredient List with Gram Sliders
                    Column(
                        verticalArrangement = Arrangement.spacedBy(8.dp),
                        modifier = Modifier
                            .fillMaxSize()
                            .verticalScroll(rememberScrollState())
                    ) {
                        allIngredients.forEach { ingredient ->
                            val currentGrams = selectedIngredientGrams[ingredient] ?: 0f
                            val isSelected = currentGrams > 0f

                            Surface(
                                modifier = Modifier.fillMaxWidth(),
                                color = surfaceColor,
                                border = BorderStroke(1.dp, if (isSelected) textColor else textColor.copy(alpha = 0.15f))
                            ) {
                                Column(modifier = Modifier.padding(12.dp)) {
                                    Row(
                                        modifier = Modifier.fillMaxWidth(),
                                        horizontalArrangement = Arrangement.SpaceBetween,
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Column {
                                            Text(
                                                text = ingredient.name,
                                                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                                                color = textColor
                                            )
                                            Text(
                                                text = "${ingredient.category} · ${ingredient.baseKcalPer100g.toInt()} kcal / ${ingredient.baseProteinPer100g}g protein per 100g",
                                                style = MaterialTheme.typography.labelSmall.copy(
                                                    fontSize = 9.sp,
                                                    color = textColor.copy(alpha = 0.5f)
                                                )
                                            )
                                        }

                                        Text(
                                            text = "${currentGrams.toInt()} G",
                                            style = MaterialTheme.typography.titleMedium.copy(
                                                fontFamily = FontFamily.Monospace,
                                                fontWeight = FontWeight.Black
                                            ),
                                            color = textColor
                                        )
                                    }

                                    Spacer(modifier = Modifier.height(6.dp))

                                    // Gram Slider (0 to 300g)
                                    Slider(
                                        value = currentGrams,
                                        onValueChange = { grams ->
                                            if (grams > 0f) {
                                                selectedIngredientGrams[ingredient] = grams
                                            } else {
                                                selectedIngredientGrams.remove(ingredient)
                                            }
                                        },
                                        valueRange = 0f..300f,
                                        steps = 29,
                                        colors = SliderDefaults.colors(
                                            thumbColor = textColor,
                                            activeTrackColor = textColor,
                                            inactiveTrackColor = textColor.copy(alpha = 0.2f)
                                        )
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
