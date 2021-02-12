package es.kiketurry.talkingabout.ui.bgg.listthings

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import es.kiketurry.talkingabout.R
import es.kiketurry.talkingabout.ui.bgg.BGGActivity
import es.kiketurry.talkingabout.ui.bgg.listusers.adapter.UserBGGViewHolder
import es.kiketurry.talkingabout.ui.calculator.supportclasses.RecyclerViewItemCountAssertion
import org.junit.Rule
import org.junit.Test

class ListThingsBGGFragmentTest {

    @get:Rule
    var activityScenarioRule: ActivityScenarioRule<BGGActivity> = ActivityScenarioRule(BGGActivity::class.java)

    @Test
    fun showListThingKiketurryDeveloperTest() {
        onView(withId(R.id.rvListUsersBGG)).perform(actionOnItemAtPosition<UserBGGViewHolder>(0, click()))
        onView(withId(R.id.rvListThingsBGG)).check(RecyclerViewItemCountAssertion(18))
    }

}