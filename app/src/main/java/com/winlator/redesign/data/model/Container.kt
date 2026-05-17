package com.winlator.redesign.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "containers")
data class Container(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val screenSize: String = "1280x720",
    val graphicsDriver: String = "vortek,gladio",
    val dxWrapper: String = "dxvk",
    val audioDriver: String = "alsa",
    val box64Preset: String = "INTERMEDIATE",
    val wineVersion: String = "wine-10.10-custom",
    val drives: String = "",
    val envVars: String = "",
    val winComponents: String = "",
    val cpuList: String = "",
    val cpuListWoW64: String = "",
    val startupSelection: Int = 0,
    val hudMode: Int = 0,
    val desktopTheme: String = "default",
    val isRunning: Boolean = false,
    val createdAt: Long = System.currentTimeMillis()
) {
    val displayDriver: String
        get() = when {
            graphicsDriver.contains("vortek") -> "Vortek"
            graphicsDriver.contains("turnip") -> "Turnip"
            graphicsDriver.contains("zink") -> "Zink"
            else -> "VirGL"
        }

    val displayDxWrapper: String
        get() = dxWrapper.uppercase()

    val displayPreset: String
        get() = when (box64Preset.uppercase()) {
            "STABILITY" -> "Stable"
            "CONSERVATIVE" -> "Conservative"
            "INTERMEDIATE" -> "Balanced"
            "PERFORMANCE" -> "Performance"
            else -> "Custom"
        }
}
