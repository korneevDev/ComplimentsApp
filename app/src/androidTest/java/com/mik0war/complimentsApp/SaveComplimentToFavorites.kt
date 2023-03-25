package com.mik0war.complimentsApp

import android.content.Intent
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.LargeTest
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.mik0war.complimentsApp.core.RecyclerViewMatcher
import com.mik0war.complimentsApp.core.lazyActivityScenarioRule
import com.mik0war.complimentsApp.data.cache.BaseRealmProvider
import com.mik0war.complimentsApp.presentation.MainActivity
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
@LargeTest
class SaveComplimentToFavorites {

    @get:Rule
    val activityScenarioRule = lazyActivityScenarioRule<MainActivity>(launchActivity = false)

    @Before
    fun before() {
        val realmProvider = BaseRealmProvider(
            ApplicationProvider.getApplicationContext(), true
        )

        realmProvider.provide().use { realm ->
            realm.executeTransaction {
                it.deleteAll()
            }
        }

        activityScenarioRule.launch(
            Intent(
                ApplicationProvider.getApplicationContext(),
                MainActivity::class.java
            )
        )
    }

    @Test
    fun test() {
        onView(
            withText(
                "There are no favorites\n" +
                        "Add new one by pressing heart icon"
            )
        )
            .check(matches(isDisplayed()))
        onView(withText("GET COMPLIMENT")).perform(click())
        onView(withText("mockCompliment 0"))
            .check(matches(isDisplayed()))

        onView(withId(R.id.favorite_icon)).perform(click())
        onView(withId(R.id.commonDataTextView))
            .check(matches(withText("mockCompliment 0")))

    }
}

@RunWith(AndroidJUnit4ClassRunner::class)
@LargeTest
class SaveTwoComplimentToFavorites {

    @get:Rule
    val activityScenarioRule = lazyActivityScenarioRule<MainActivity>(launchActivity = false)

    @Before
    fun before() {
        val realmProvider = BaseRealmProvider(
            ApplicationProvider.getApplicationContext(), true
        )

        realmProvider.provide().use { realm ->
            realm.executeTransaction {
                it.deleteAll()
            }
        }

        activityScenarioRule.launch(
            Intent(
                ApplicationProvider.getApplicationContext(),
                MainActivity::class.java
            )
        )
    }

    @Test
    fun test_two_items() {
        onView(withText("There are no favorites\n" +
                "Add new one by pressing heart icon")).check(matches(isDisplayed()))
        onView(withText("GET COMPLIMENT")).perform(click())
        onView(withText("mockCompliment 0")).check(matches(isDisplayed()))

        onView(withId(R.id.favorite_icon)).perform(click())
        onView(RecyclerViewMatcher(R.id.recycler_view).atPosition(0, R.id.commonDataTextView))
            .check(matches(withText("mockCompliment 0")))

        onView(withText("GET COMPLIMENT")).perform(click())
        onView(withText("mockCompliment 1")).check(matches(isDisplayed()))
        onView(withId(R.id.favorite_icon)).perform(click())
        onView(RecyclerViewMatcher(R.id.recycler_view).atPosition(1, R.id.commonDataTextView))
            .check(matches(withText("mockCompliment 1")))
    }
}