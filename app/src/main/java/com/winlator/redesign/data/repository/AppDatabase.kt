package com.winlator.redesign.data.repository

import android.content.Context
import androidx.room.*
import com.winlator.redesign.data.model.Container
import com.winlator.redesign.data.model.InputProfile
import com.winlator.redesign.data.model.Shortcut
import kotlinx.coroutines.flow.Flow

@Dao
interface ContainerDao {
    @Query("SELECT * FROM containers ORDER BY createdAt DESC")
    fun getAllFlow(): Flow<List<Container>>

    @Query("SELECT * FROM containers ORDER BY createdAt DESC")
    suspend fun getAll(): List<Container>

    @Query("SELECT * FROM containers WHERE id = :id")
    suspend fun getById(id: Int): Container?

    @Query("SELECT COUNT(*) FROM containers")
    fun getCount(): Flow<Int>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(container: Container): Long

    @Update
    suspend fun update(container: Container)

    @Delete
    suspend fun delete(container: Container)

    @Query("DELETE FROM containers WHERE id = :id")
    suspend fun deleteById(id: Int)
}

@Dao
interface ShortcutDao {
    @Query("SELECT * FROM shortcuts ORDER BY createdAt DESC")
    fun getAllFlow(): Flow<List<Shortcut>>

    @Query("SELECT * FROM shortcuts WHERE containerId = :containerId")
    fun getByContainerFlow(containerId: Int): Flow<List<Shortcut>>

    @Query("SELECT COUNT(*) FROM shortcuts")
    fun getCount(): Flow<Int>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(shortcut: Shortcut): Long

    @Update
    suspend fun update(shortcut: Shortcut)

    @Delete
    suspend fun delete(shortcut: Shortcut)
}

@Dao
interface InputProfileDao {
    @Query("SELECT * FROM input_profiles ORDER BY createdAt DESC")
    fun getAllFlow(): Flow<List<InputProfile>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(profile: InputProfile): Long

    @Update
    suspend fun update(profile: InputProfile)

    @Delete
    suspend fun delete(profile: InputProfile)
}

@Database(
    entities = [Container::class, Shortcut::class, InputProfile::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun containerDao(): ContainerDao
    abstract fun shortcutDao(): ShortcutDao
    abstract fun inputProfileDao(): InputProfileDao

    companion object {
        @Volatile private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "winlator.db"
                ).build().also { INSTANCE = it }
            }
        }
    }
}
