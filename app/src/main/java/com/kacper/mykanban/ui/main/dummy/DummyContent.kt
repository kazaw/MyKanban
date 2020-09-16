package com.kacper.mykanban.ui.main.dummy

import android.graphics.Color
import com.kacper.mykanban.data.KanbanCard
import java.util.*
import com.kacper.mykanban.utilities.TO_DO

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 *
 *
 */
object DummyContent {

    private val ITEMS: MutableList<KanbanCard> = ArrayList()
    private val ITEM_MAP: MutableMap<String, KanbanCard> = HashMap()
    private const val COUNT = 25

    init {
        for (i in 1..COUNT) {
            addItem(createDummyItem(i))
        }
    }

    private fun addItem(item: KanbanCard) {
        ITEMS.add(item)
        ITEM_MAP[item.uid.toString()] = item
    }

    private fun createDummyItem(position: Int): KanbanCard {
        return if (position%2 == 1) KanbanCard("Dummy $position", TO_DO , "Description $position", Color.CYAN, Calendar.getInstance())
        else KanbanCard("Bob $position", TO_DO , "Description $position", Color.GRAY, Calendar.getInstance())
    }

    data class DummyItem(val id: String, val content: String, val details: String) {
        override fun toString(): String = content
    }
}