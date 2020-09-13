package com.kacper.mykanban.data
import androidx.lifecycle.LiveData
import androidx.room.Query
import com.kacper.mykanban.data.KanbanCardDao
import com.kacper.mykanban.utilities.DATABASE_TABLE_CARD

class KanbanCardRepository (private val kanbanCardDao: KanbanCardDao){
    fun getAll() = kanbanCardDao.getAll()

    fun getAllByColor(kanbanColor: Int) = kanbanCardDao.getAllByColor(kanbanColor)

    fun getAllByType(kanbanType: String) = kanbanCardDao.getAllByType(kanbanType)

    fun getKanbanCard(kanbanUid: Int) = kanbanCardDao.getKanbanCard(kanbanUid)

    suspend fun insert(kanbanCard: KanbanCard) = kanbanCardDao.insert(kanbanCard)

    suspend fun update(kanbanCard: KanbanCard) = kanbanCardDao.update(kanbanCard)

    suspend fun delete(kanbanCard: KanbanCard) = kanbanCardDao.delete(kanbanCard)

    suspend fun deleteAll() = kanbanCardDao.deleteAll()


}