package com.winlator.redesign.ui.containers

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.winlator.redesign.data.model.Container
import com.winlator.redesign.data.repository.AppDatabase
import com.winlator.redesign.data.repository.ContainerRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class ContainersViewModel(application: Application) : AndroidViewModel(application) {

    private val repo = ContainerRepository(AppDatabase.getInstance(application).containerDao())

    val containers: StateFlow<List<Container>> = repo.allContainers
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    fun createContainer(name: String, screenSize: String, graphicsDriver: String, dxWrapper: String) {
        viewModelScope.launch {
            repo.create(
                Container(
                    name = name,
                    screenSize = screenSize,
                    graphicsDriver = graphicsDriver,
                    dxWrapper = dxWrapper
                )
            )
        }
    }

    fun deleteContainer(container: Container) {
        viewModelScope.launch { repo.delete(container) }
    }

    fun cloneContainer(container: Container) {
        viewModelScope.launch { repo.clone(container) }
    }

    fun updateContainer(container: Container) {
        viewModelScope.launch { repo.update(container) }
    }
}
