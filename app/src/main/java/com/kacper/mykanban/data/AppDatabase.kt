package com.kacper.mykanban.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = arrayOf(KanbanCard::class), version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun KanbanCardDao() : KanbanCardDao

}