package com.lara.kaya.data.model

enum class MuscleGroup(val displayName: String, val categoryGroup: String) {
    QUADS("Quads", "Quads / Glutes"),
    HAMSTRINGS("Hamstrings", "Quads / Glutes"),
    GLUTES("Glutes", "Quads / Glutes"),
    CALVES("Calves", "Quads / Glutes"),
    ADDUCTORS("Adductors", "Quads / Glutes"),
    
    FRONT_DELTS("Front Delts", "Chest / Delts"),
    SIDE_DELTS("Side Delts", "Chest / Delts"),
    REAR_DELTS("Rear Delts", "Chest / Delts"),
    UPPER_CHEST("Upper Chest", "Chest / Delts"),
    MID_CHEST("Mid Chest", "Chest / Delts"),
    LOWER_CHEST("Lower Chest", "Chest / Delts"),
    
    BICEPS("Biceps", "Arms / Core"),
    FOREARMS("Forearms", "Arms / Core"),
    TRICEPS_LONG("Triceps Long Head", "Arms / Core"),
    TRICEPS_LATERAL("Triceps Lateral", "Arms / Core"),
    TRICEPS_COMPOUND("Triceps Compound", "Arms / Core"),
    UPPER_LOWER_ABS("Upper & Lower Abs", "Arms / Core"),
    OBLIQUES("Obliques", "Arms / Core"),
    DEEP_CORE("Deep Core", "Arms / Core"),
    
    LATS("Lats", "Back / Traps"),
    MID_BACK("Mid Back", "Back / Traps"),
    TRAPS("Traps", "Back / Traps"),
    LOWER_BACK("Lower Back", "Back / Traps"),
    
    CARDIO_TREADMILL("Treadmill", "Cardio"),
    CARDIO_CYCLING("Cycling", "Cardio"),
    CARDIO_STAIR("Stair Climber", "Cardio"),
    CARDIO_ROPE("Jump Rope", "Cardio"),
    CARDIO_ROWING("Rowing Machine", "Cardio"),
    CARDIO_ELLIPTICAL("Elliptical", "Cardio"),
    CARDIO_HIIT("HIIT Circuit", "Cardio")
}
