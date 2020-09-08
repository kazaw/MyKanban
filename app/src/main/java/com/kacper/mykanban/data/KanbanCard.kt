package com.kacper.mykanban.data

import android.graphics.Color
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Calendar


@Entity
data class KanbanCard(
    var name : String,
    var type : String,
    var description : String,
    var color: Int,
    var calendar: Calendar
) {
    @PrimaryKey(autoGenerate = true) var uid: Int = 0
    override fun toString() = name
}