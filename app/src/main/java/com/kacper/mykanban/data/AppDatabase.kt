package com.kacper.mykanban.data

import android.content.Context
import android.graphics.Color
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import com.kacper.mykanban.utilities.DONE
import com.kacper.mykanban.utilities.IN_PROGRESS
import com.kacper.mykanban.utilities.TO_DO
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import java.util.*

@Database(entities = [KanbanCard::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun kanbanCardDao(): KanbanCardDao
    companion object {
        @Volatile
        private  var INSTANCE: AppDatabase? = null
        fun getDatabase(context: Context, scope: CoroutineScope) : AppDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "word_database"
                ).addCallback(AppDatabaseCallback(scope)).build()
                INSTANCE = instance
                return instance
            }
        }
    }

    private class AppDatabaseCallback(
        private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {

        override fun onOpen(db: SupportSQLiteDatabase) {
            super.onOpen(db)
            INSTANCE?.let { database ->
                scope.launch {
                    val kanbanCardDao = database.kanbanCardDao()
                    //kanbanCardDao.deleteAll()
/*                    kanbanCardDao.insert(
                        KanbanCard("Dummy 1", TO_DO , "Description 1", Color.RED, Calendar.getInstance())
                    )
                    kanbanCardDao.insert(
                        KanbanCard("Dummy 2", IN_PROGRESS , "Description 2", Color.BLUE, Calendar.getInstance())
                    )
                    kanbanCardDao.insert(
                        KanbanCard("Dummy 3", DONE , "Description 3", Color.GREEN, Calendar.getInstance())
                    )*/
                }
            }
        }
    }

}
