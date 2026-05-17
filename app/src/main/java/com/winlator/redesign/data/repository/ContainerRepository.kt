package com.winlator.redesign.data.repository

import com.winlator.redesign.data.model.Container
import kotlinx.coroutines.flow.Flow

class ContainerRepository(private val dao: ContainerDao) {

    val allContainers: Flow<List<Container>> = dao.getAllFlow()
    val containerCount: Flow<Int> = dao.getCount()

    suspend fun getById(id: Int): Container? = dao.getById(id)

    suspend fun create(container: Container): Long = dao.insert(container)

    suspend fun update(container: Container) = dao.update(container)

    suspend fun delete(container: Container) = dao.delete(container)

    suspend fun deleteById(id: Int) = dao.deleteById(id)

    suspend fun clone(container: Container): Long {
        val cloned = container.copy(
            id = 0,
            name = "${container.name} (Clone)",
            createdAt = System.currentTimeMillis()
        )
        return dao.insert(cloned)
    }
}
