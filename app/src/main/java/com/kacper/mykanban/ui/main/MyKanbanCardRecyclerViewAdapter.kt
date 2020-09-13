package com.kacper.mykanban.ui.main

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.kacper.mykanban.R
import com.kacper.mykanban.data.KanbanCard
import com.kacper.mykanban.utilities.CellClickListener


/**
 * [RecyclerView.Adapter] that can display a [KanbanCard].
 *
 */
class MyKanbanCardRecyclerViewAdapter(
    private val context: Context,
    private var values: MutableList<KanbanCard>,
    private val cellClickListener: CellClickListener
) : RecyclerView.Adapter<MyKanbanCardRecyclerViewAdapter.ViewHolder>() {

    fun swapData(values: List<KanbanCard>){
        this.values.clear()
        this.values.addAll(values)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_kanbancard, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.contentView.text = item.name
        holder.itemView.setBackgroundColor(item.color)
        holder.itemView.setOnClickListener {
            cellClickListener.onCellClickListener()
        }
    }
    override fun getItemCount(): Int = values.size

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val contentView: TextView = view.findViewById(R.id.content)

        override fun toString(): String {
            return super.toString() + " '" + contentView.text + "'"
        }
    }

}
