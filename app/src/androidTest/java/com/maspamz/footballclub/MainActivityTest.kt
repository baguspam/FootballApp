package com.maspamz.footballclub

import android.support.test.espresso.Espresso.*
import android.support.test.espresso.action.ViewActions
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.action.ViewActions.typeText
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.support.v7.widget.RecyclerView
import com.maspamz.footballclub.R.id.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import android.support.test.espresso.matcher.ViewMatchers.isDescendantOfA
import org.hamcrest.CoreMatchers.allOf


/**
 * Created by Maspamz on 20/09/2018.
 *
 */

@RunWith(AndroidJUnit4::class)
class MainActivityTest{
    @Rule
    @JvmField var activityRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun testAppBehaviour(){
        //Club Testing
            onView(withId(l_menu_club)).check(matches(isDisplayed()))
            onView(withId(l_menu_club)).perform(click())
        Thread.sleep(2000)
            onView(withId(l_search_view)).check(matches(isDisplayed()))
            onView(withId(l_search_view)).perform(typeText("Arsenal"),click())
        Thread.sleep(4000)
            onView(allOf(isDisplayed(),withId(lrv_view_club))).check(matches(isDisplayed()))
            //onView(allOf(isDisplayed(),withText("Arsenal"))).check(matches(isDisplayed()))
            onView(allOf(isDisplayed(), withId(lrv_view_club)))
                    .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        Thread.sleep(3000)
            onView(withId(l_tabs_main)).check(matches(isDisplayed()))
            val matcher1 = allOf(withText("Club Player"), isDescendantOfA(withId(l_tabs_main)))
            onView(matcher1).perform(click())
        Thread.sleep(2000)
            onView(withId(lrv_view_player)).check(matches(isDisplayed()))
            onView(withId(lrv_view_player))
                .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        Thread.sleep(3000)
        pressBack()
            onView(withId(add_to_favorite)).check(matches(isDisplayed()))
            onView(withId(add_to_favorite)).perform(click())
            onView(withText(R.string.add_favorite))
                    .check(matches(isDisplayed()))
        Thread.sleep(2000)
        pressBack()
            onView(withId(l_search_view)).check(matches(isDisplayed()))
            onView(withId(l_search_view)).perform(typeText(""),click())
        Thread.sleep(3000)
        pressBack()
        pressBack()
        Thread.sleep(2000)
            onView(withId(l_menu_favorite)).check(matches(isDisplayed()))
            onView(withId(l_menu_favorite)).perform(click())
        Thread.sleep(2000)
            onView(withId(l_tab_favorite)).check(matches(isDisplayed()))
            val matcher = allOf(withText("Club"), isDescendantOfA(withId(l_tab_favorite)))
            onView(matcher).perform(click())
        Thread.sleep(2000)
            onView(allOf(isDisplayed(), withId(lrv_view_favorite))).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        Thread.sleep(2000)
            onView(withId(add_to_favorite)).check(matches(isDisplayed()))
            onView(withId(add_to_favorite)).perform(click())
            onView(withText(R.string.remove_favorite))
                    .check(matches(isDisplayed()))
        pressBack()
            onView(allOf(isDisplayed(), withId(l_swipe_favorite))).perform(ViewActions.swipeDown())
        Thread.sleep(3000)

    }

}