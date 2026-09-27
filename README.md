# ⚡ K A Y A — High-Performance AMOLED Fitness Architecture

[![Android](https://img.shields.io/badge/Platform-Android%208.0%2B-black?style=for-the-badge&logo=android)](https://developer.android.com)
[![Kotlin](https://img.shields.io/badge/Language-Kotlin%202.2.10-black?style=for-the-badge&logo=kotlin)](https://kotlinlang.org)
[![Compose](https://img.shields.io/badge/UI-Jetpack%20Compose%20Material3-black?style=for-the-badge&logo=jetpackcompose)](https://developer.android.com/jetpack/compose)
[![Room DB](https://img.shields.io/badge/Database-Room%20DB-black?style=for-the-badge)](https://developer.android.com/training/data-storage/room)
[![Supabase](https://img.shields.io/badge/Backend-Supabase%20REST-black?style=for-the-badge&logo=supabase)](https://supabase.com)

**KAYA** is a production-ready, offline-first Android application built with **Jetpack Compose**, **Material 3**, **Room Database**, **Jetpack Media3 (ExoPlayer)**, **Android Health Connect API**, and **Supabase REST Auth & Sync**.

Designed with a true **AMOLED Monochrome High-Contrast theme** (`#000000` background, `#FFFFFF` text/accents, `#141414` surface cards) and an **Invert Color Switch (`🌓`)** for universal colorblind accessibility.

---

## 📥 Direct APK Download

<a href="app-debug.apk">
  <img src="https://img.shields.io/badge/DOWNLOAD_KAYA_APK-000000?style=for-the-badge&logo=android&logoColor=white&labelColor=111111" alt="Download KAYA APK" height="50">
</a>

> **Filename**: `app-debug.apk`  
> **Package**: `com.lara.kaya`  
> **Min SDK**: 26 (Android 8.0+) | **Target SDK**: 35 (Android 15+)

---

## 🏛️ System Architecture

Kaya strictly enforces **Clean Architecture** with the **MVI / MVVM Pattern** and **Offline-First Data Storage**. Raw workout logs and meal catalogs remain on local Room DB (optimizing Supabase free-tier 500MB limits), syncing high-level daily aggregate summaries online once per day.

```
+-------------------------------------------------------------------+
|                        UI / Compose Layer                         |
|  HomeScreen | TrainScreen | DietScreen | StatsScreen | MoreScreen |
+-------------------------------------------------------------------+
                                  |
                                  v
+-------------------------------------------------------------------+
|                      KayaViewModel (MVI/MVVM)                     |
|  - Realtime Flow Observation  - Invert Theme State                |
|  - Thread-Safe Dispatchers.IO - Audio & Sound Effects             |
+-------------------------------------------------------------------+
        |                         |                         |
        v                         v                         v
+------------------+    +-------------------+    +------------------+
|     Room DB      |    |  Media3 ExoPlayer |    |  Health Connect  |
| (Preseeded Assets|    | & Sound Effects   |    |  & Supabase Auth |
| Exercises/Meals) |    | (Phonk Player)    |    |  & Postgrest REST|
+------------------+    +-------------------+    +------------------+
```

---

## 📱 Feature Highlights

### 1. 🌓 AMOLED High-Contrast Theme & Universal Invert Toggle
- **AMOLED Dark Mode**: True `#000000` background, crisp `#FFFFFF` typography, dark gray card surfaces.
- **Paper Light Mode**: Instant 1-tap color inversion (`#FFFFFF` background, `#000000` text) across all screens.

### 2. 🏠 Home Dashboard
- Dynamic Date Header (`SUNDAY — SEP 27`) & `GOOD MORNING, ATHLETE.` greeting.
- **Caloric Expenditure Card**: Numeric progress bar (`480 / 650 KCAL` · `74%`).
- **Daily Protein Target Card**: Numeric progress bar (`92 / 140 G` · `66%`).
- **2x2 Muscle Recovery Grid**: Realtime recovery status with solid and dashed border badges:
  - `CHEST / DELTS` (`READY TO TRAIN`, `● RECOVERED`)
  - `QUADS / GLUTES` (`RECOVERING: 28H LEFT` with dashed border, `◌ RECOVERING`)
  - `BACK / TRAPS` (`READY TO TRAIN`, `● RECOVERED`)
  - `ARMS / CORE` (`REC: 6H LEFT` with dashed border, `◌ RECOVERING`)
- Prominent `START TODAY'S WORKOUT →` CTA button.

### 3. 🏋️ Active Workout Session & Stopwatch (Train)
- Active Session Header: `BENCH PRESS (MID CHEST) — SET 2 / 4`.
- **Millisecond Digital Stopwatch**: `00:45:12.4` with tap-to-start/pause toggle.
- Set Progress Indicators (`── ── ── ──`).
- **Rotating Motivational Quote Ticker**: Cycles resilience quotes every 15s (e.g. `— DISCIPLINE IS THE ONLY SHORTCUT. —`).
- **Embedded Phonk Media3 Audio Player**: Local audio controller playing bundled Phonk music with play/pause and skip controls.
- **Log Set Modal Dialog**: Modal dialog to record completed load (kg) and repetitions.

### 4. 🥗 Meal Composer & Nutrition Log (Diet)
- **10 Quick Meals Tab**: 1-click logging for pre-seeded athlete meals (Dal Khichdi, Paneer Bhurji, Chicken Rice, Oats Bowl, Egg White Omelette, Moong Dal Soup, Rajma Chawal, Soya Pulao, Almond Oats, Grilled Fish).
- **Custom Recipe Builder**: Multi-select pre-seeded ingredient catalog (Grains, Legumes, Meats, Dairy, Oils, Spices) with gram sliders (0–300g) and real-time aggregated Dish Calories and Dish Protein formulas.

### 5. 📊 Performance Analytics (Stats)
- **Monochromatic Hatched Weekly Volume Bar Chart**: `54,200 KG` weekly volume display with white hatched pattern fill for active days and dashed outlines for rest days.
- **Health Connect Status Card**: Live step counter (`8,420 / 10K`) and active kcal counter (`380 / 650`), last sync timestamp (`SYNC: 09:38 AM · SEP 26 ● LIVE`).
- **1RM Personal Records List**: Bench Press (`100 KG [+5KG]`), Deadlift (`160 KG [+10KG]`), Squat (`140 KG [+5KG]`).

### 6. ⚙️ Settings & Supabase Control (More)
- Athlete Biometric Profile (Height cm, Weight kg, Gender, Calorie Goal, Protein Goal).
- **Supabase Free-Tier Sync Control**: Offline-first strategy keeping raw logs local and syncing daily summary aggregates.
- **Pre-seeded Exercise Catalog Browser**: Searchable list of all pre-seeded exercises across 25+ muscle groups.

---

## 🧪 Core Physiological Calculations

$$\text{Calories} = \left(\frac{\text{Exercise\_MET} \times 3.5 \times \text{Weight\_Kg}}{200}\right) \times \left(\frac{\text{Duration\_Sec}}{60}\right) \times \left(1.0 + \frac{\text{Reps} - 10}{100}\right)$$

$$\text{Protein\_Target} = (\text{Weight\_Kg} \times 0.83) + (\text{Total\_Sets\_Logged} \times 0.05)$$

$$\text{Dish\_Calories} = \sum \left(\frac{\text{Grams}}{100} \times \text{Base\_Kcal}\right)$$

$$\text{Dish\_Protein} = \sum \left(\frac{\text{Grams}}{100} \times \text{Base\_Protein}\right)$$

---

## 🛠️ Tech Stack & Dependencies

| Layer | Technology |
| :--- | :--- |
| **Language** | Kotlin 2.2.10 |
| **UI Framework** | Jetpack Compose + Material 3 |
| **Local Database** | Room DB 2.6.1 + KSP |
| **Audio Playback** | Jetpack Media3 (ExoPlayer 1.5.1) + ToneGenerator |
| **Telemetry** | Android Health Connect API |
| **Backend & Sync** | Supabase REST Auth & Postgrest |
| **Architecture** | MVI / MVVM + Coroutines / Flow |

---

## 🚀 Building & Running Locally

1. **Clone the Repository**:
   ```bash
   git clone https://github.com/YourUsername/Kaya.git
   cd Kaya
   ```

2. **Open in Android Studio**:
   - Open Android Studio (Ladybug / Hedgehog+).
   - Perform a **Gradle Sync**.

3. **Build & Run**:
   ```bash
   ./gradlew app:assembleDebug
   adb install -r app/build/outputs/apk/debug/app-debug.apk
   ```

---

## 📄 License
Designed & Engineered for High-Performance Athletes. All rights reserved.
