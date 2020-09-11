package com.kacper.mykanban.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kacper.mykanban.data.AppDatabase
import com.kacper.mykanban.data.KanbanCard
import com.kacper.mykanban.data.KanbanCardRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class KanbanCardViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: KanbanCardRepository
    val all: LiveData<List<KanbanCard>>
    init {
        val kanbanCardDao = AppDatabase.getDatabase(application).kanbanCardDao()
        repository = KanbanCardRepository(kanbanCardDao)
        all = repository.getAll()
    }

    fun insert(kanbanCard: KanbanCard) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(kanbanCard)
    }
}