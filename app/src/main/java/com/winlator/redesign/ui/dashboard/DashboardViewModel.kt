package com.winlator.redesign.ui.dashboard

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.winlator.redesign.data.repository.AppDatabase
import com.winlator.redesign.data.repository.ContainerRepository
import com.winlator.redesign.data.repository.ShortcutRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn

class DashboardViewModel(application: Application) : AndroidViewModel(application) {

    private val db = AppDatabase.getInstance(application)
    private val containerRepo = ContainerRepository(db.containerDao())
    private val shortcutRepo = ShortcutRepository(db.shortcutDao())

    val containerCount: StateFlow<Int> = containerRepo.containerCount
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), 0)

    val shortcutCount: StateFlow<Int> = shortcutRepo.shortcutCount
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), 0)

    val activeCount: StateFlow<Int> = containerCount
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), 0)
}
