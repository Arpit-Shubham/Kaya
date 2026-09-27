package com.lara.kaya.data.local

import com.lara.kaya.data.local.entity.ExerciseEntity
import com.lara.kaya.data.local.entity.IngredientEntity
import com.lara.kaya.data.local.entity.QuickMealEntity
import com.lara.kaya.data.model.MuscleGroup

object PreseedData {

    val exercises = listOf(
        // Quads
        ExerciseEntity(name = "Squats", muscleGroup = MuscleGroup.QUADS, defaultMET = 6.0f, recoveryHours = 72),
        ExerciseEntity(name = "Leg Press", muscleGroup = MuscleGroup.QUADS, defaultMET = 6.0f, recoveryHours = 72),
        ExerciseEntity(name = "Leg Extension", muscleGroup = MuscleGroup.QUADS, defaultMET = 6.0f, recoveryHours = 72),
        ExerciseEntity(name = "Lunges", muscleGroup = MuscleGroup.QUADS, defaultMET = 6.0f, recoveryHours = 72),
        ExerciseEntity(name = "Hack Squat", muscleGroup = MuscleGroup.QUADS, defaultMET = 6.0f, recoveryHours = 72),

        // Hamstrings
        ExerciseEntity(name = "Romanian Deadlift", muscleGroup = MuscleGroup.HAMSTRINGS, defaultMET = 6.0f, recoveryHours = 72),
        ExerciseEntity(name = "Leg Curl", muscleGroup = MuscleGroup.HAMSTRINGS, defaultMET = 6.0f, recoveryHours = 72),
        ExerciseEntity(name = "Deadlift", muscleGroup = MuscleGroup.HAMSTRINGS, defaultMET = 6.0f, recoveryHours = 72),
        ExerciseEntity(name = "Good Morning", muscleGroup = MuscleGroup.HAMSTRINGS, defaultMET = 6.0f, recoveryHours = 72),

        // Glutes
        ExerciseEntity(name = "Hip Thrust", muscleGroup = MuscleGroup.GLUTES, defaultMET = 5.5f, recoveryHours = 72),
        ExerciseEntity(name = "Glute Bridge", muscleGroup = MuscleGroup.GLUTES, defaultMET = 5.5f, recoveryHours = 72),
        ExerciseEntity(name = "Bulgarian Split Squat", muscleGroup = MuscleGroup.GLUTES, defaultMET = 5.5f, recoveryHours = 72),
        ExerciseEntity(name = "Kickback", muscleGroup = MuscleGroup.GLUTES, defaultMET = 5.5f, recoveryHours = 72),

        // Calves
        ExerciseEntity(name = "Standing Calf Raise", muscleGroup = MuscleGroup.CALVES, defaultMET = 4.0f, recoveryHours = 48),
        ExerciseEntity(name = "Seated Calf Raise", muscleGroup = MuscleGroup.CALVES, defaultMET = 4.0f, recoveryHours = 48),
        ExerciseEntity(name = "Leg Press Calf Raise", muscleGroup = MuscleGroup.CALVES, defaultMET = 4.0f, recoveryHours = 48),

        // Adductors
        ExerciseEntity(name = "Adductor Machine", muscleGroup = MuscleGroup.ADDUCTORS, defaultMET = 4.5f, recoveryHours = 48),
        ExerciseEntity(name = "Sumo Squat", muscleGroup = MuscleGroup.ADDUCTORS, defaultMET = 4.5f, recoveryHours = 48),
        ExerciseEntity(name = "Cossack Squat", muscleGroup = MuscleGroup.ADDUCTORS, defaultMET = 4.5f, recoveryHours = 48),

        // Delts
        ExerciseEntity(name = "Shoulder Press", muscleGroup = MuscleGroup.FRONT_DELTS, defaultMET = 5.0f, recoveryHours = 48),
        ExerciseEntity(name = "Arnold Press", muscleGroup = MuscleGroup.FRONT_DELTS, defaultMET = 5.0f, recoveryHours = 48),
        ExerciseEntity(name = "Front Raise", muscleGroup = MuscleGroup.FRONT_DELTS, defaultMET = 5.0f, recoveryHours = 48),
        ExerciseEntity(name = "Lateral Raise", muscleGroup = MuscleGroup.SIDE_DELTS, defaultMET = 4.0f, recoveryHours = 48),
        ExerciseEntity(name = "Cable Lateral Raise", muscleGroup = MuscleGroup.SIDE_DELTS, defaultMET = 4.0f, recoveryHours = 48),
        ExerciseEntity(name = "Machine Lateral Raise", muscleGroup = MuscleGroup.SIDE_DELTS, defaultMET = 4.0f, recoveryHours = 48),
        ExerciseEntity(name = "Reverse Fly", muscleGroup = MuscleGroup.REAR_DELTS, defaultMET = 4.0f, recoveryHours = 48),
        ExerciseEntity(name = "Face Pull", muscleGroup = MuscleGroup.REAR_DELTS, defaultMET = 4.0f, recoveryHours = 48),
        ExerciseEntity(name = "Reverse Pec Deck", muscleGroup = MuscleGroup.REAR_DELTS, defaultMET = 4.0f, recoveryHours = 48),

        // Biceps & Forearms
        ExerciseEntity(name = "Barbell Curl", muscleGroup = MuscleGroup.BICEPS, defaultMET = 4.0f, recoveryHours = 48),
        ExerciseEntity(name = "Dumbbell Curl", muscleGroup = MuscleGroup.BICEPS, defaultMET = 4.0f, recoveryHours = 48),
        ExerciseEntity(name = "Preacher Curl", muscleGroup = MuscleGroup.BICEPS, defaultMET = 4.0f, recoveryHours = 48),
        ExerciseEntity(name = "Cable Curl", muscleGroup = MuscleGroup.BICEPS, defaultMET = 4.0f, recoveryHours = 48),
        ExerciseEntity(name = "Hammer Curl", muscleGroup = MuscleGroup.FOREARMS, defaultMET = 4.0f, recoveryHours = 36),
        ExerciseEntity(name = "Reverse Curl", muscleGroup = MuscleGroup.FOREARMS, defaultMET = 4.0f, recoveryHours = 36),
        ExerciseEntity(name = "Zottman Curl", muscleGroup = MuscleGroup.FOREARMS, defaultMET = 4.0f, recoveryHours = 36),

        // Triceps
        ExerciseEntity(name = "Overhead Extension", muscleGroup = MuscleGroup.TRICEPS_LONG, defaultMET = 4.0f, recoveryHours = 48),
        ExerciseEntity(name = "Skull Crusher", muscleGroup = MuscleGroup.TRICEPS_LONG, defaultMET = 4.0f, recoveryHours = 48),
        ExerciseEntity(name = "Triceps Pushdown", muscleGroup = MuscleGroup.TRICEPS_LATERAL, defaultMET = 3.8f, recoveryHours = 48),
        ExerciseEntity(name = "Reverse-Grip Pushdown", muscleGroup = MuscleGroup.TRICEPS_LATERAL, defaultMET = 3.8f, recoveryHours = 48),
        ExerciseEntity(name = "Close-Grip Bench Press", muscleGroup = MuscleGroup.TRICEPS_COMPOUND, defaultMET = 5.5f, recoveryHours = 48),
        ExerciseEntity(name = "Dips", muscleGroup = MuscleGroup.TRICEPS_COMPOUND, defaultMET = 5.5f, recoveryHours = 48),
        ExerciseEntity(name = "Diamond Push-Ups", muscleGroup = MuscleGroup.TRICEPS_COMPOUND, defaultMET = 5.5f, recoveryHours = 48),

        // Back
        ExerciseEntity(name = "Pull-Ups", muscleGroup = MuscleGroup.LATS, defaultMET = 5.5f, recoveryHours = 72),
        ExerciseEntity(name = "Lat Pulldown", muscleGroup = MuscleGroup.LATS, defaultMET = 5.5f, recoveryHours = 72),
        ExerciseEntity(name = "Straight-Arm Pulldown", muscleGroup = MuscleGroup.LATS, defaultMET = 5.5f, recoveryHours = 72),
        ExerciseEntity(name = "Dumbbell Row", muscleGroup = MuscleGroup.LATS, defaultMET = 5.5f, recoveryHours = 72),
        ExerciseEntity(name = "Seated Row", muscleGroup = MuscleGroup.MID_BACK, defaultMET = 5.5f, recoveryHours = 72),
        ExerciseEntity(name = "T-Bar Row", muscleGroup = MuscleGroup.MID_BACK, defaultMET = 5.5f, recoveryHours = 72),
        ExerciseEntity(name = "Chest-Supported Row", muscleGroup = MuscleGroup.MID_BACK, defaultMET = 5.5f, recoveryHours = 72),
        ExerciseEntity(name = "Shrugs", muscleGroup = MuscleGroup.TRAPS, defaultMET = 4.5f, recoveryHours = 48),
        ExerciseEntity(name = "Farmer's Carry", muscleGroup = MuscleGroup.TRAPS, defaultMET = 4.5f, recoveryHours = 48),
        ExerciseEntity(name = "Conventional Deadlift", muscleGroup = MuscleGroup.LOWER_BACK, defaultMET = 6.0f, recoveryHours = 72),
        ExerciseEntity(name = "Back Extension", muscleGroup = MuscleGroup.LOWER_BACK, defaultMET = 6.0f, recoveryHours = 72),

        // Chest
        ExerciseEntity(name = "Incline Bench Press", muscleGroup = MuscleGroup.UPPER_CHEST, defaultMET = 5.5f, recoveryHours = 72),
        ExerciseEntity(name = "Incline Dumbbell Press", muscleGroup = MuscleGroup.UPPER_CHEST, defaultMET = 5.5f, recoveryHours = 72),
        ExerciseEntity(name = "Low-to-High Cable Fly", muscleGroup = MuscleGroup.UPPER_CHEST, defaultMET = 5.5f, recoveryHours = 72),
        ExerciseEntity(name = "Bench Press", muscleGroup = MuscleGroup.MID_CHEST, defaultMET = 5.5f, recoveryHours = 72),
        ExerciseEntity(name = "Dumbbell Press", muscleGroup = MuscleGroup.MID_CHEST, defaultMET = 5.5f, recoveryHours = 72),
        ExerciseEntity(name = "Chest Fly", muscleGroup = MuscleGroup.MID_CHEST, defaultMET = 5.5f, recoveryHours = 72),
        ExerciseEntity(name = "Pec Deck", muscleGroup = MuscleGroup.MID_CHEST, defaultMET = 5.5f, recoveryHours = 72),
        ExerciseEntity(name = "Decline Bench Press", muscleGroup = MuscleGroup.LOWER_CHEST, defaultMET = 5.5f, recoveryHours = 72),
        ExerciseEntity(name = "Chest Dips", muscleGroup = MuscleGroup.LOWER_CHEST, defaultMET = 5.5f, recoveryHours = 72),
        ExerciseEntity(name = "High-to-Low Cable Fly", muscleGroup = MuscleGroup.LOWER_CHEST, defaultMET = 5.5f, recoveryHours = 72),

        // Core / Abs
        ExerciseEntity(name = "Crunches", muscleGroup = MuscleGroup.UPPER_LOWER_ABS, defaultMET = 3.8f, recoveryHours = 24),
        ExerciseEntity(name = "Sit-Ups", muscleGroup = MuscleGroup.UPPER_LOWER_ABS, defaultMET = 3.8f, recoveryHours = 24),
        ExerciseEntity(name = "Leg Raises", muscleGroup = MuscleGroup.UPPER_LOWER_ABS, defaultMET = 3.8f, recoveryHours = 24),
        ExerciseEntity(name = "Reverse Crunch", muscleGroup = MuscleGroup.UPPER_LOWER_ABS, defaultMET = 3.8f, recoveryHours = 24),
        ExerciseEntity(name = "Russian Twist", muscleGroup = MuscleGroup.OBLIQUES, defaultMET = 3.8f, recoveryHours = 36),
        ExerciseEntity(name = "Side Plank", muscleGroup = MuscleGroup.OBLIQUES, defaultMET = 3.8f, recoveryHours = 36),
        ExerciseEntity(name = "Cable Woodchop", muscleGroup = MuscleGroup.OBLIQUES, defaultMET = 3.8f, recoveryHours = 36),
        ExerciseEntity(name = "Plank", muscleGroup = MuscleGroup.DEEP_CORE, defaultMET = 3.5f, recoveryHours = 24),
        ExerciseEntity(name = "Dead Bug", muscleGroup = MuscleGroup.DEEP_CORE, defaultMET = 3.5f, recoveryHours = 24),
        ExerciseEntity(name = "Ab Wheel", muscleGroup = MuscleGroup.DEEP_CORE, defaultMET = 3.5f, recoveryHours = 24),
        ExerciseEntity(name = "Pallof Press", muscleGroup = MuscleGroup.DEEP_CORE, defaultMET = 3.5f, recoveryHours = 24),

        // Cardio
        ExerciseEntity(name = "Treadmill Walking/Running", muscleGroup = MuscleGroup.CARDIO_TREADMILL, defaultMET = 8.0f, recoveryHours = 24),
        ExerciseEntity(name = "Stationary Cycling", muscleGroup = MuscleGroup.CARDIO_CYCLING, defaultMET = 7.0f, recoveryHours = 24),
        ExerciseEntity(name = "Stair Climber", muscleGroup = MuscleGroup.CARDIO_STAIR, defaultMET = 9.0f, recoveryHours = 24),
        ExerciseEntity(name = "Jump Rope", muscleGroup = MuscleGroup.CARDIO_ROPE, defaultMET = 11.0f, recoveryHours = 24),
        ExerciseEntity(name = "Rowing Machine", muscleGroup = MuscleGroup.CARDIO_ROWING, defaultMET = 8.0f, recoveryHours = 24),
        ExerciseEntity(name = "Elliptical Trainer", muscleGroup = MuscleGroup.CARDIO_ELLIPTICAL, defaultMET = 6.0f, recoveryHours = 24),
        ExerciseEntity(name = "HIIT Circuit", muscleGroup = MuscleGroup.CARDIO_HIIT, defaultMET = 9.5f, recoveryHours = 24)
    )

    val ingredients = listOf(
        // Grains & Staples
        IngredientEntity(name = "White Rice", category = "Grains", baseKcalPer100g = 130f, baseProteinPer100g = 2.7f),
        IngredientEntity(name = "Brown Rice", category = "Grains", baseKcalPer100g = 111f, baseProteinPer100g = 2.6f),
        IngredientEntity(name = "Whole Wheat Atta", category = "Grains", baseKcalPer100g = 340f, baseProteinPer100g = 13.2f),
        IngredientEntity(name = "Rolled Oats", category = "Grains", baseKcalPer100g = 389f, baseProteinPer100g = 16.9f),
        IngredientEntity(name = "Quinoa", category = "Grains", baseKcalPer100g = 120f, baseProteinPer100g = 4.4f),
        IngredientEntity(name = "Whole Wheat Bread", category = "Grains", baseKcalPer100g = 247f, baseProteinPer100g = 13.0f),
        IngredientEntity(name = "Pasta", category = "Grains", baseKcalPer100g = 131f, baseProteinPer100g = 5.0f),
        IngredientEntity(name = "Foxtail Millet", category = "Grains", baseKcalPer100g = 331f, baseProteinPer100g = 12.3f),
        IngredientEntity(name = "Boiled Potato", category = "Staples", baseKcalPer100g = 87f, baseProteinPer100g = 1.9f),
        IngredientEntity(name = "Sweet Potato", category = "Staples", baseKcalPer100g = 86f, baseProteinPer100g = 1.6f),

        // Beans & Legumes
        IngredientEntity(name = "Boiled Chickpeas", category = "Beans", baseKcalPer100g = 164f, baseProteinPer100g = 8.9f),
        IngredientEntity(name = "Rajma (Kidney Beans)", category = "Beans", baseKcalPer100g = 127f, baseProteinPer100g = 8.7f),
        IngredientEntity(name = "Moong Dal", category = "Legumes", baseKcalPer100g = 105f, baseProteinPer100g = 7.0f),
        IngredientEntity(name = "Masoor Dal", category = "Legumes", baseKcalPer100g = 116f, baseProteinPer100g = 9.0f),
        IngredientEntity(name = "Soya Chunks", category = "Legumes", baseKcalPer100g = 345f, baseProteinPer100g = 52.0f),
        IngredientEntity(name = "Peanuts", category = "Nuts & Legumes", baseKcalPer100g = 567f, baseProteinPer100g = 25.8f),

        // Vegetables & Aromatics
        IngredientEntity(name = "Onion", category = "Vegetables", baseKcalPer100g = 40f, baseProteinPer100g = 1.1f),
        IngredientEntity(name = "Tomato", category = "Vegetables", baseKcalPer100g = 18f, baseProteinPer100g = 0.9f),
        IngredientEntity(name = "Garlic", category = "Aromatics", baseKcalPer100g = 149f, baseProteinPer100g = 6.4f),
        IngredientEntity(name = "Spinach", category = "Vegetables", baseKcalPer100g = 23f, baseProteinPer100g = 2.9f),
        IngredientEntity(name = "Broccoli", category = "Vegetables", baseKcalPer100g = 34f, baseProteinPer100g = 2.8f),
        IngredientEntity(name = "Paneer", category = "Dairy", baseKcalPer100g = 265f, baseProteinPer100g = 18.3f),

        // Meats & Seafood
        IngredientEntity(name = "Chicken Breast", category = "Meat", baseKcalPer100g = 165f, baseProteinPer100g = 31.0f),
        IngredientEntity(name = "Chicken Thigh", category = "Meat", baseKcalPer100g = 209f, baseProteinPer100g = 26.0f),
        IngredientEntity(name = "Salmon Fillet", category = "Seafood", baseKcalPer100g = 208f, baseProteinPer100g = 20.4f),
        IngredientEntity(name = "Canned Tuna", category = "Seafood", baseKcalPer100g = 116f, baseProteinPer100g = 25.5f),
        IngredientEntity(name = "Mutton / Lamb", category = "Meat", baseKcalPer100g = 294f, baseProteinPer100g = 25.0f),
        IngredientEntity(name = "Whole Egg", category = "Eggs", baseKcalPer100g = 155f, baseProteinPer100g = 12.6f),
        IngredientEntity(name = "Egg White", category = "Eggs", baseKcalPer100g = 52f, baseProteinPer100g = 11.0f),

        // Dairy & Oils
        IngredientEntity(name = "Curd / Dahi", category = "Dairy", baseKcalPer100g = 60f, baseProteinPer100g = 3.5f),
        IngredientEntity(name = "Greek Yogurt", category = "Dairy", baseKcalPer100g = 59f, baseProteinPer100g = 10.0f),
        IngredientEntity(name = "Tofu", category = "Vegan Dairy", baseKcalPer100g = 76f, baseProteinPer100g = 8.0f),
        IngredientEntity(name = "Almonds", category = "Nuts", baseKcalPer100g = 579f, baseProteinPer100g = 21.2f),
        IngredientEntity(name = "Walnuts", category = "Nuts", baseKcalPer100g = 654f, baseProteinPer100g = 15.2f),
        IngredientEntity(name = "Chia Seeds", category = "Seeds", baseKcalPer100g = 486f, baseProteinPer100g = 16.5f),
        IngredientEntity(name = "Olive Oil", category = "Oils", baseKcalPer100g = 884f, baseProteinPer100g = 0.0f),
        IngredientEntity(name = "Ghee", category = "Oils", baseKcalPer100g = 899f, baseProteinPer100g = 0.0f)
    )

    val quickMeals = listOf(
        QuickMealEntity(name = "DAL KHICHDI", kcal = 380, proteinGrams = 18f, emojiIcon = "🍲"),
        QuickMealEntity(name = "PANEER BHURJI", kcal = 520, proteinGrams = 28f, emojiIcon = "🧀"),
        QuickMealEntity(name = "CHICKEN RICE", kcal = 680, proteinGrams = 45f, emojiIcon = "🍚"),
        QuickMealEntity(name = "OATS PROTEIN BOWL", kcal = 290, proteinGrams = 24f, emojiIcon = "🥣"),
        QuickMealEntity(name = "EGG WHITE OMELETTE", kcal = 180, proteinGrams = 26f, emojiIcon = "🍳"),
        QuickMealEntity(name = "MOONG DAL SOUP", kcal = 220, proteinGrams = 16f, emojiIcon = "🍜"),
        QuickMealEntity(name = "RAJMA CHAWAL", kcal = 440, proteinGrams = 22f, emojiIcon = "🫘"),
        QuickMealEntity(name = "SOYA CHUNK PULAO", kcal = 545, proteinGrams = 32.5f, emojiIcon = "🍲"),
        QuickMealEntity(name = "ALMOND-CHIA OATS", kcal = 512, proteinGrams = 19.8f, emojiIcon = "🥣"),
        QuickMealEntity(name = "GRILLED FISH FILLET", kcal = 398, proteinGrams = 39.2f, emojiIcon = "🐟")
    )
}
