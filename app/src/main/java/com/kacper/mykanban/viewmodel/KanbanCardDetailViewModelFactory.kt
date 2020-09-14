package com.kacper.mykanban.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class KanbanCardDetailViewModelFactory(
    private val application: Application
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(KanbanCardDetailViewModel::class.java)){
            return KanbanCardDetailViewModel(application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}