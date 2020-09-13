package com.kacper.mykanban.data

import android.graphics.Color
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.kacper.mykanban.utilities.DATABASE_TABLE_CARD
import java.io.Serializable
import java.util.Calendar


@Entity(tableName = DATABASE_TABLE_CARD)
data class KanbanCard (
    var name : String,
    var type : String,
    var description : String,
    var color: Int,
    var calendar: Calendar,
) : Serializable{
    @PrimaryKey(autoGenerate = true) var uid: Int = 0
    override fun toString() = name
}