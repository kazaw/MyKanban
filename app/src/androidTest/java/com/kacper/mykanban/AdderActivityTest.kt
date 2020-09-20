package com.kacper.mykanban

import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.matcher.IntentMatchers
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.rules.ActivityScenarioRule
import org.junit.Rule
import org.junit.Test

class AdderActivityTest {

    @Rule
    @JvmField
    var activityScenarioRule = ActivityScenarioRule(AdderActivity::class.java)

    @Test
    fun testButtonInsert(){
        Intents.init()
        onView(ViewMatchers.withId(R.id.editText_name)).perform(ViewActions.typeText("testButtonInsertName"))
        onView(ViewMatchers.withId(R.id.editText_description)).perform(ViewActions.typeText("testButtonInsertDescription"))
        onView(ViewMatchers.withId(R.id.editText_name)).perform(ViewActions.closeSoftKeyboard())
        onView(ViewMatchers.withId(R.id.button_insert)).perform(click())
        Intents.intended(IntentMatchers.hasComponent(MainActivity::class.java.name))
        Intents.release()
    }
}