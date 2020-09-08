package com.kacper.mykanban.ui.main

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.kacper.mykanban.R
import com.kacper.mykanban.data.KanbanCard

import com.kacper.mykanban.ui.main.dummy.DummyContent.DummyItem

/**
 * [RecyclerView.Adapter] that can display a [KanbanCard].
 * TODO: Replace the implementation with code for your data type.
 */
class MyKanbanCardRecyclerViewAdapter(
    private val values: List<KanbanCard>
) : RecyclerView.Adapter<MyKanbanCardRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_kanbancard, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.idView.text = item.uid.toString()
        holder.contentView.text = item.name
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val idView: TextView = view.findViewById(R.id.item_number)
        val contentView: TextView = view.findViewById(R.id.content)

        override fun toString(): String {
            return super.toString() + " '" + contentView.text + "'"
        }
    }
}