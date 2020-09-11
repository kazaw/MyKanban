package com.kacper.mykanban.data

import android.graphics.Color
import androidx.lifecycle.LiveData
import androidx.room.*
import com.kacper.mykanban.utilities.DATABASE_TABLE_CARD

@Dao
interface KanbanCardDao {
    @Query("SELECT * FROM $DATABASE_TABLE_CARD")
    fun getAll(): LiveData<List<KanbanCard>>

    @Query("SELECT * FROM $DATABASE_TABLE_CARD WHERE color = :kanbanColor")
    fun getAllByColor(kanbanColor: Int): LiveData<List<KanbanCard>>

    @Query("SELECT * FROM $DATABASE_TABLE_CARD WHERE type = :kanbanType")
    fun getAllByType(kanbanType: String): LiveData<List<KanbanCard>>

    @Query("SELECT * FROM $DATABASE_TABLE_CARD WHERE uid = :kanbanUid")
    fun getKanbanCard(kanbanUid: Int) : LiveData<KanbanCard>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(kanbanCard: KanbanCard)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(kanbanCards: List<KanbanCard>)

    @Update
    suspend fun update(kanbanCard: KanbanCard)

    @Delete
    suspend fun delete(kanbanCard: KanbanCard)
}