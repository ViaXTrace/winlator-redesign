package com.winlator.redesign.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "input_profiles")
data class InputProfile(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val profileData: String = "{}",
    val isDefault: Boolean = false,
    val createdAt: Long = System.currentTimeMillis()
)
