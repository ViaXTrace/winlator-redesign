package com.winlator.redesign.data.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "shortcuts",
    foreignKeys = [ForeignKey(
        entity = Container::class,
        parentColumns = ["id"],
        childColumns = ["containerId"],
        onDelete = ForeignKey.CASCADE
    )]
)
data class Shortcut(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val execPath: String,
    val args: String = "",
    val containerId: Int,
    val containerName: String = "",
    val workDir: String = "C:\\",
    val iconPath: String = "",
    val createdAt: Long = System.currentTimeMillis()
)
