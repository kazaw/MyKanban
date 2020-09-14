package com.kacper.mykanban.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kacper.mykanban.data.AppDatabase
import com.kacper.mykanban.data.KanbanCard
import com.kacper.mykanban.data.KanbanCardRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class KanbanCardDetailViewModel(application: Application) : ViewModel() {
    private val repository: KanbanCardRepository
    init {
        val kanbanCardDao = AppDatabase.getDatabase(application, viewModelScope).kanbanCardDao()
        repository = KanbanCardRepository(kanbanCardDao)
    }

    fun getKanbanCard(kanbanCard: KanbanCard) : LiveData<KanbanCard>{
        return repository.getKanbanCard(kanbanCard.uid)
    }

    fun update(kanbanCard: KanbanCard) = viewModelScope.launch(Dispatchers.IO) {
        repository.update(kanbanCard)
    }

    fun delete(kanbanCard: KanbanCard) = viewModelScope.launch(Dispatchers.IO) {
        repository.delete(kanbanCard)
    }
}