package com.kacper.mykanban.ui.main

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.kacper.mykanban.R
import com.kacper.mykanban.utilities.DONE
import com.kacper.mykanban.utilities.IN_PROGRESS
import com.kacper.mykanban.utilities.TO_DO
import java.lang.Exception

private val TAB_TITLES = arrayOf(
        R.string.tab_text_1,
        R.string.tab_text_2,
        R.string.tab_text_3
)

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
@Suppress("DEPRECATION")
class SectionsPagerAdapter(private val context: Context, fm: FragmentManager)
    : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        return when(position){
            0 -> KanbanCardFragment.newInstance(1, TO_DO)
            1 -> KanbanCardFragment.newInstance(1, IN_PROGRESS)
            2 -> KanbanCardFragment.newInstance(1, DONE)
            else -> throw Exception("getItem Exception")
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return context.resources.getString(TAB_TITLES[position])
    }

    override fun getCount(): Int {
        // Show 3 total pages.
        return 3
    }
}