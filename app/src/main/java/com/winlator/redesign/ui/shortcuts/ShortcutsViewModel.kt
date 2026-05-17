package com.winlator.redesign.ui.shortcuts

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.winlator.redesign.data.model.Shortcut
import com.winlator.redesign.data.repository.AppDatabase
import com.winlator.redesign.data.repository.ShortcutRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class ShortcutsViewModel(application: Application) : AndroidViewModel(application) {

    private val repo = ShortcutRepository(AppDatabase.getInstance(application).shortcutDao())

    val shortcuts: StateFlow<List<Shortcut>> = repo.allShortcuts
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    fun delete(shortcut: Shortcut) {
        viewModelScope.launch { repo.delete(shortcut) }
    }
}
