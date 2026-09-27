package com.lara.kaya.domain

import com.lara.kaya.data.local.entity.IngredientEntity

object Calculations {

    /**
     * Calories = (Exercise_MET * 3.5 * User_Weight_Kg / 200) * (Duration_Seconds / 60) * (1.0 + (Reps - 10) / 100)
     */
    fun calculateCaloricExpenditure(
        met: Float,
        userWeightKg: Float,
        durationSeconds: Long,
        reps: Int
    ): Float {
        val baseKcalPerMin = (met * 3.5f * userWeightKg) / 200f
        val durationMins = durationSeconds / 60f
        val repMultiplier = 1.0f + ((reps - 10) / 100f)
        return (baseKcalPerMin * durationMins * repMultiplier).coerceAtLeast(0f)
    }

    /**
     * Protein_Target = (User_Weight_Kg * 0.83) + (Total_Sets_Logged * 0.05)
     */
    fun calculateProteinTarget(
        userWeightKg: Float,
        totalSetsLogged: Int
    ): Float {
        return (userWeightKg * 0.83f) + (totalSetsLogged * 0.05f)
    }

    /**
     * Dish_Calories = Sum((Ingredient_Grams / 100) * Base_Kcal)
     */
    fun calculateDishCalories(
        selectedIngredients: Map<IngredientEntity, Float>
    ): Float {
        return selectedIngredients.entries.sumOf { (ingredient, grams) ->
            ((grams / 100f) * ingredient.baseKcalPer100g).toDouble()
        }.toFloat()
    }

    /**
     * Dish_Protein = Sum((Ingredient_Grams / 100) * Base_Protein)
     */
    fun calculateDishProtein(
        selectedIngredients: Map<IngredientEntity, Float>
    ): Float {
        return selectedIngredients.entries.sumOf { (ingredient, grams) ->
            ((grams / 100f) * ingredient.baseProteinPer100g).toDouble()
        }.toFloat()
    }
}
