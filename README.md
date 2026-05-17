# Winlator Redesign

Complete glassmorphism UI/UX redesign of the Winlator Android app — built from scratch.

## Design System

- **Theme**: Glassmorphism on deep space dark background
- **Background**: `#0A0E27` → `#0F1B45` → `#1A0A3C` gradient
- **Glass cards**: Semi-transparent white with frosted border
- **Accents**: Cyan `#00E5FF` · Purple `#7C4DFF` · Green `#00FF88`
- **Icons**: 20+ custom SVG vector drawables

## Screens

| Screen | Description |
|--------|-------------|
| Splash | Animated logo with glow entrance animation |
| Dashboard | Stats overview + system info cards |
| Containers | Full CRUD Wine environment manager |
| Shortcuts | Game/app grid launcher |
| Settings | Grouped preference panels |

## Architecture

- **Language**: Kotlin
- **UI**: XML layouts + ViewBinding
- **Pattern**: MVVM (ViewModel + LiveData/Flow)
- **Database**: Room (SQLite)
- **Navigation**: Navigation Component
- **Async**: Kotlin Coroutines + Flow
- **DI**: Manual (no framework)

## Tech Stack

```
minSdk 26 (Android 8.0)
targetSdk 34 (Android 14)
Kotlin 1.9.23
AGP 8.3.1
Gradle 8.7
Material Design 3
```

## Building

### GitHub Actions (Recommended)
Trigger the **Build & Release** workflow manually from GitHub Actions.

### Local
```bash
# Install Gradle 8.7 first
gradle assembleRelease
# APK: app/build/outputs/apk/release/
```

## About

This project is a complete UI/UX redesign demonstration for [Winlator](https://github.com/brunodev85/winlator), an Android app that enables running Windows applications using Wine and Box64.
