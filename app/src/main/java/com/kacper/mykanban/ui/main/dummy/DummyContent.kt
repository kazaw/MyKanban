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

    /**
     * An array of sample (dummy) items.
     */
    val ITEMS: MutableList<KanbanCard> = ArrayList()

    /**
     * A map of sample (dummy) items, by ID.
     */
    val ITEM_MAP: MutableMap<String, KanbanCard> = HashMap()

    private val COUNT = 25

    init {
        // Add some sample items.
        for (i in 1..COUNT) {
            addItem(createDummyItem(i))
        }
    }

    private fun addItem(item: KanbanCard) {
        ITEMS.add(item)
        ITEM_MAP[item.uid.toString()] = item
    }

    private fun createDummyItem(position: Int): KanbanCard {
        return KanbanCard("Dummy $position", TO_DO , "Description $position", Color.CYAN, Calendar.getInstance())
    }



    /**
     * A dummy item representing a piece of content.
     */
    data class DummyItem(val id: String, val content: String, val details: String) {
        override fun toString(): String = content
    }
}