package com.mik0war.complimentsApp

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.filters.LargeTest
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.mik0war.complimentsApp.presentation.MainActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
@LargeTest
class RandomComplimentTest {

    @get:Rule
    val activityScenarioRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun test(){
        onView(withText("GET COMPLIMENT")).perform(click())
        onView(withText("mockCompliment 0")).check(matches(isDisplayed()))

        onView(withText("GET COMPLIMENT")).perform(click())
        onView(withText("mockCompliment 1")).check(matches(isDisplayed()))

    }
}