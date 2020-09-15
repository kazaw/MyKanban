package com.kacper.mykanban.utilities

import com.kacper.mykanban.data.KanbanCard

interface CellClickListener {
    fun onCellClickListener(kanbanCard: KanbanCard)
}