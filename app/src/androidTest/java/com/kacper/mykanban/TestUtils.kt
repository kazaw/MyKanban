package com.kacper.mykanban

import com.kacper.mykanban.ui.main.MyKanbanCardRecyclerViewAdapter
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher

const val STRING_TO_BE_TYPED = "MainActivityTestString"

fun matchName(): Matcher<MyKanbanCardRecyclerViewAdapter.ViewHolder?>? {
    return object : TypeSafeMatcher<MyKanbanCardRecyclerViewAdapter.ViewHolder?>() {

        override fun describeTo(description: Description) {
            description.appendText("matched name")
        }
        override fun matchesSafely(item: MyKanbanCardRecyclerViewAdapter.ViewHolder?): Boolean {
            return item?.contentView?.text == STRING_TO_BE_TYPED
        }
    }
}