package com.kacper.mykanban.data

import android.graphics.Color
import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface KanbanCardDao {
    @Query("SELECT * FROM kanbanCard")
    fun getAll(): LiveData<List<KanbanCard>>

    @Query("SELECT * FROM kanbanCard WHERE color = :kanbanColor")
    fun getAllByColor(kanbanColor: Color): LiveData<List<KanbanCard>>

    @Query("SELECT * FROM kanbanCard WHERE type = :kanbanType")
    fun getAllByType(kanbanType: String): LiveData<List<KanbanCard>>

    @Query("SELECT * FROM kanbanCard WHERE uid = :kanbanUid")
    fun getKanbanCard(kanbanUid: Int) : LiveData<KanbanCard>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg kanbanCard: KanbanCard)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(kanbanCards: List<KanbanCard>)

    @Delete
    fun delete(kanbanCard: KanbanCard)
}