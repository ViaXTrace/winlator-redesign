package com.winlator.redesign.data.repository

import com.winlator.redesign.data.model.Shortcut
import kotlinx.coroutines.flow.Flow

class ShortcutRepository(private val dao: ShortcutDao) {

    val allShortcuts: Flow<List<Shortcut>> = dao.getAllFlow()
    val shortcutCount: Flow<Int> = dao.getCount()

    fun getByContainer(containerId: Int): Flow<List<Shortcut>> =
        dao.getByContainerFlow(containerId)

    suspend fun create(shortcut: Shortcut): Long = dao.insert(shortcut)

    suspend fun update(shortcut: Shortcut) = dao.update(shortcut)

    suspend fun delete(shortcut: Shortcut) = dao.delete(shortcut)
}
