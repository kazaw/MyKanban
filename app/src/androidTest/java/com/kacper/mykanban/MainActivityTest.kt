package com.kacper.mykanban

import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.platform.app.InstrumentationRegistry.getInstrumentation
import com.kacper.mykanban.ui.main.MyKanbanCardRecyclerViewAdapter
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import org.hamcrest.TypeSafeMatcher
import org.junit.Rule
import org.junit.Test


open class MainActivityTest {

    @Rule @JvmField
    var activityScenarioRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun testOpenAdderActivity(){
        Intents.init()
        onView(withId(R.id.fab)).perform(click())
        Intents.intended(hasComponent(AdderActivity::class.java.name))
        Intents.release()
    }

    @Test
    fun testClickTabs(){
        Intents.init()
        onView(withId(R.id.tabs)).perform(click())
        Intents.release()
    }

    @Test
    fun testAddNewCard(){
        Intents.init()
        onView(withId(R.id.fab)).perform(click())
        onView(withId(R.id.editText_name)).perform(ViewActions.typeText(STRING_TO_BE_TYPED))
        onView(withId(R.id.editText_description)).perform(
            ViewActions.typeText(
                STRING_TO_BE_TYPED
            )
        )
        onView(withId(R.id.editText_name)).perform(ViewActions.closeSoftKeyboard())
        onView(withId(R.id.button_insert)).perform(click())
        Intents.release()
    }

    @Test
    fun afterTestCleanup(){
        Intents.init()
        //TODO: Click field in recyclerView with testAddNewCardName
        onView(allOf(withId(R.id.recyclerView_list), isDisplayed()))
            .perform(
                RecyclerViewActions.actionOnHolderItem(matchName(), click())
            )
        openActionBarOverflowOrOptionsMenu(getInstrumentation().targetContext)
        //onView(withText(R.string.text_delete)).perform(click())
        onView(withId(R.id.action_delete)).perform(click());
        Intents.release()
    }
}