package com.kacper.mykanban.ui.main

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.kacper.mykanban.AdderActivity
import com.kacper.mykanban.R
import com.kacper.mykanban.data.KanbanCard
import com.kacper.mykanban.utilities.REQUEST_EDIT
import com.kacper.mykanban.utilities.REQUEST_INSERT
import com.kacper.mykanban.viewmodel.KanbanCardDetailViewModel
import com.kacper.mykanban.viewmodel.KanbanCardDetailViewModelFactory
import kotlinx.android.synthetic.main.fragment_kanbancard_detail.*
import java.text.SimpleDateFormat

class KanbanCardDetailFragment : DialogFragment(), Toolbar.OnMenuItemClickListener   {

    companion object {

        private const val KANBAN_KEY = "kanban_key"

        fun newInstance(kanbanCard: KanbanCard) : KanbanCardDetailFragment{
            val fragment = KanbanCardDetailFragment()
            val args = Bundle().apply {
                putSerializable(KANBAN_KEY, kanbanCard)
            }
            fragment.arguments = args
            return fragment
        }
    }

    private lateinit var viewModelFactory: KanbanCardDetailViewModelFactory
    private lateinit var viewModel: KanbanCardDetailViewModel
    private lateinit var kanbanCard: KanbanCard

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModelFactory = KanbanCardDetailViewModelFactory(activity!!.application)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(
            KanbanCardDetailViewModel::class.java
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_kanbancard_detail, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        toolbar_detail.setNavigationOnClickListener{
            dismiss()
        }
        toolbar_detail.inflateMenu(R.menu.menu_detail)
        toolbar_detail.setOnMenuItemClickListener(this)
        arguments?.getSerializable(KANBAN_KEY)?.let { kanbanCard = it as KanbanCard }
        viewModel.getKanbanCard(kanbanCard).observe(viewLifecycleOwner, {
            this.kanbanCard = it
            displayDetails()
        })
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
    }
    
    @SuppressLint("SimpleDateFormat")
    private fun displayDetails(){
        val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd hh:mm")
        textView_detail_name.text = kanbanCard.name
        textView_detail_type.text = kanbanCard.type
        textView_detail_date.text = simpleDateFormat.format(kanbanCard.calendar.time)
        textView_detail_description.text = kanbanCard.description
        toolbar_detail.setBackgroundColor(kanbanCard.color)
    }

    private fun deleteCurrent(){
        viewModel.delete(kanbanCard)
        dismiss()
    }

    private fun startEditActivity(){
        val intent = Intent(activity, AdderActivity::class.java)
        intent.putExtra("KanbanCardToEditExtra",kanbanCard)
        intent.putExtra("requestCode", REQUEST_EDIT)
        startActivityForResult(intent, REQUEST_EDIT)
    }

    override fun onMenuItemClick(item: MenuItem?): Boolean {
        return when (item?.itemId){
            R.id.action_edit -> {
                startEditActivity()
                true
            }
            R.id.action_delete -> {
                deleteCurrent()
                true
            }
            else -> false
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_EDIT && resultCode == AppCompatActivity.RESULT_OK) {
            val kanbanCard : KanbanCard = data?.getSerializableExtra("EditedKanbanCardExtra") as KanbanCard
            viewModel.update(kanbanCard)
            Toast.makeText(context, "Edited", Toast.LENGTH_SHORT).show()
        }
    }
}