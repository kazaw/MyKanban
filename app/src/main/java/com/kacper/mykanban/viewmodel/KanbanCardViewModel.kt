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
    init {
        val kanbanCardDao = AppDatabase.getDatabase(application, viewModelScope).kanbanCardDao()
        repository = KanbanCardRepository(kanbanCardDao)
    }

    fun getAll(): LiveData<List<KanbanCard>> {
        return  repository.getAll()
    }

    fun getAllByType(kanbanType: String): LiveData<List<KanbanCard>> {
        return  repository.getAllByType(kanbanType)
    }

    fun insert(kanbanCard: KanbanCard) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(kanbanCard)
    }
}