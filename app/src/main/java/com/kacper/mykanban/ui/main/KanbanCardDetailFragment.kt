package com.kacper.mykanban.ui.main

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.kacper.mykanban.R
import com.kacper.mykanban.data.KanbanCard
import com.kacper.mykanban.viewmodel.KanbanCardDetailViewModel
import com.kacper.mykanban.viewmodel.KanbanCardDetailViewModelFactory
import com.kacper.mykanban.viewmodel.KanbanCardViewModelFactory
import kotlinx.android.synthetic.main.kanban_card_detail_fragment.*

class KanbanCardDetailFragment : DialogFragment() {

    companion object {

        private const val KANBAN_KEY = "kanban_key"

        fun newInstance(kanbanCard: KanbanCard) : KanbanCardDetailFragment{
            val fragment = KanbanCardDetailFragment()
            val args = Bundle().apply {
                putSerializable(KANBAN_KEY, kanbanCard)
            }
            return fragment
        }
    }

    private lateinit var viewModelFactory: KanbanCardDetailViewModelFactory
    private lateinit var viewModel: KanbanCardDetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.kanban_card_detail_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        toolbar.setNavigationOnClickListener{
            dismiss()
        }
        viewModelFactory = KanbanCardDetailViewModelFactory(activity!!.application)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(
            KanbanCardDetailViewModel::class.java)
        // TODO: Use the ViewModel
    }

}