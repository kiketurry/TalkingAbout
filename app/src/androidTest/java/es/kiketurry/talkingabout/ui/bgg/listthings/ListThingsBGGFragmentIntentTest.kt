package es.kiketurry.talkingabout.ui.bgg.listthings

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.matcher.IntentMatchers.hasExtra
import androidx.test.espresso.intent.matcher.IntentMatchers.toPackage
import androidx.test.espresso.intent.rule.IntentsTestRule
import androidx.test.espresso.matcher.ViewMatchers.withId
import es.kiketurry.talkingabout.R
import es.kiketurry.talkingabout.data.constants.IntentKeys
import es.kiketurry.talkingabout.data.repository.bbdd.AppDatabase
import es.kiketurry.talkingabout.data.repository.bbdd.things.ThingBGGDao
import es.kiketurry.talkingabout.ui.bgg.BGGActivity
import es.kiketurry.talkingabout.ui.bgg.listthings.adapter.ThingBGGViewHolder
import es.kiketurry.talkingabout.ui.bgg.listusers.adapter.UserBGGViewHolder
import es.kiketurry.talkingabout.ui.calculator.supportclasses.ClickViewIdItemRecyclerViewAction
import org.junit.Rule
import org.junit.Test

class ListThingsBGGFragmentIntentTest {

    @get:Rule
    val intentsTestRule = IntentsTestRule(BGGActivity::class.java)

    private lateinit var thingBGGDao: ThingBGGDao
    private lateinit var db: AppDatabase

    @Test
    fun validateIntentSentWhatsapp() {
        onView(withId(R.id.rvListUsersBGG)).perform(actionOnItemAtPosition<UserBGGViewHolder>(0, click()))
        onView(withId(R.id.rvListThingsBGG)).perform(actionOnItemAtPosition<ThingBGGViewHolder>(0, ClickViewIdItemRecyclerViewAction(R.id.ivWhatsapp)))
        intended(toPackage("com.whatsapp"))
    }

//    @Test
//    fun validateIntentSentWhatsappTwo() {
//        onView(withId(R.id.rvListUsersBGG)).perform(actionOnItemAtPosition<UserBGGViewHolder>(0, click()))
////        onView(withId(R.id.ivWhatsapp)).perform(click())
//
////        onData(withId(R.id.ivWhatsapp))
////            .inAdapterView(withId(R.id.rvListThingsBGG))
////            .atPosition(0)
////            .perform(click())
//
//        onView(withId(R.id.rvListThingsBGG)).perform(actionOnItemAtPosition<ThingBGGViewHolder>(0, ClickViewIdItemRecyclerViewAction(R.id.ivWhatsapp)))
//
//        intended(toPackage("com.whatsapp"))
//    }

    @Test
    fun validateIntentCalculatorDetail() {
        onView(withId(R.id.rvListUsersBGG)).perform(actionOnItemAtPosition<UserBGGViewHolder>(0, click()))
        onView(withId(R.id.btLaunchIntent)).perform(click())
        intended(hasExtra(IntentKeys.INTENT_KEY_CALCULATOR_DETAIL, "Todo ha salido perfecto."))
    }

}