package com.kacper.mykanban

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager.widget.ViewPager
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.tabs.TabLayout
import com.kacper.mykanban.data.KanbanCard
import com.kacper.mykanban.ui.main.SectionsPagerAdapter
import com.kacper.mykanban.utilities.REQUEST_INSERT
import com.kacper.mykanban.viewmodel.KanbanCardViewModel
import com.kacper.mykanban.viewmodel.KanbanCardViewModelFactory

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val sectionsPagerAdapter = SectionsPagerAdapter(this, supportFragmentManager)
        val viewPager: ViewPager = findViewById(R.id.view_pager)
        viewPager.adapter = sectionsPagerAdapter
        val tabs: TabLayout = findViewById(R.id.tabs)
        tabs.setupWithViewPager(viewPager)

        var kanbanCardViewModelFactory = KanbanCardViewModelFactory(application)
        var kanbanCardViewModel = ViewModelProvider(this, kanbanCardViewModelFactory).get(KanbanCardViewModel::class.java)
/*        kanbanCardViewModel.all.observe(this, Observer { cards ->
            cards.let {}//TODO: SOMETHING
        })*/


        val fab: FloatingActionButton = findViewById(R.id.fab)
        fab.setOnClickListener {
            val intent = Intent(this@MainActivity, AdderActivity::class.java)
            startActivityForResult(intent, REQUEST_INSERT)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_INSERT && resultCode == RESULT_OK) {
            val kanbanCard : KanbanCard = data?.getSerializableExtra("KanbanCardExtra") as KanbanCard

        }
    }
}