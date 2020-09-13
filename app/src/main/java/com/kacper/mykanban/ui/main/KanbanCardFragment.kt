package com.kacper.mykanban.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.kacper.mykanban.R
import com.kacper.mykanban.data.KanbanCard
import com.kacper.mykanban.ui.main.dummy.DummyContent
import com.kacper.mykanban.utilities.CellClickListener
import com.kacper.mykanban.viewmodel.KanbanCardViewModel
import com.kacper.mykanban.viewmodel.KanbanCardViewModelFactory
import java.util.ArrayList

/**
 * A fragment representing a list of Items.
 */
class KanbanCardFragment : Fragment(), CellClickListener {

    private var columnCount = 1
    private lateinit var kanbanCardViewModelFactory: KanbanCardViewModelFactory
    private lateinit var kanbanCardViewModel: KanbanCardViewModel
    private lateinit var kanbanAdapter : MyKanbanCardRecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        kanbanCardViewModelFactory = KanbanCardViewModelFactory(activity!!.application)
        kanbanCardViewModel = ViewModelProvider(this, kanbanCardViewModelFactory).get(
            KanbanCardViewModel::class.java)

        arguments?.let {
            columnCount = it.getInt(ARG_COLUMN_COUNT)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_kanbancard_list, container, false)

        // Set the adapter

        if (view is RecyclerView) {
            with(view) {
                layoutManager = when {
                    columnCount <= 1 -> LinearLayoutManager(context)
                    else -> GridLayoutManager(context, columnCount)
                }
                kanbanAdapter = MyKanbanCardRecyclerViewAdapter(context, mutableListOf(), this@KanbanCardFragment)
                //this@KanbanCardFragment.adapter = MyKanbanCardRecyclerViewAdapter(context, DummyContent.ITEMS, this@KanbanCardFragment)
                adapter = kanbanAdapter
                kanbanCardViewModel.getAll().observe(this@KanbanCardFragment, { cards ->
                    kanbanAdapter.swapData(cards)
                })

            }
        }
        return view
    }

    companion object {

        // TODO: Customize parameter argument names
        const val ARG_COLUMN_COUNT = "column-count"

        // TODO: Customize parameter initialization
        @JvmStatic
        fun newInstance(columnCount: Int) =
            KanbanCardFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_COLUMN_COUNT, columnCount)
                }
            }
    }

    override fun onCellClickListener() {
        Toast.makeText(context,"Cell clicked", Toast.LENGTH_SHORT).show()
    }

}
