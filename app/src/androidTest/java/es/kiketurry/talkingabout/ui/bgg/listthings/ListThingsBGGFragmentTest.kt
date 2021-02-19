package es.kiketurry.talkingabout.ui.bgg.listthings

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.platform.app.InstrumentationRegistry
import es.kiketurry.talkingabout.R
import es.kiketurry.talkingabout.data.repository.bbdd.AppDatabase
import es.kiketurry.talkingabout.data.repository.bbdd.mapper.bgg.ThingBGGMapperBBDD
import es.kiketurry.talkingabout.data.repository.bbdd.things.ThingBGGDao
import es.kiketurry.talkingabout.injection.InjectionSingleton
import es.kiketurry.talkingabout.ui.bgg.BGGActivity
import es.kiketurry.talkingabout.ui.bgg.listusers.adapter.UserBGGViewHolder
import es.kiketurry.talkingabout.ui.calculator.supportclasses.RecyclerViewItemCountAssertion
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers.containsString
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.io.IOException

class ListThingsBGGFragmentTest {

    @get:Rule
    var activityScenarioRule: ActivityScenarioRule<BGGActivity> = ActivityScenarioRule(BGGActivity::class.java)

    private lateinit var thingBGGDao: ThingBGGDao
    private lateinit var db: AppDatabase

    @Before
    fun createDb() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        db = InjectionSingleton.provideAppDatabase(context)
        thingBGGDao = db.ThingBGGDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    fun showListThingKiketurryDeveloperTest() {
        onView(withId(R.id.rvListUsersBGG)).perform(actionOnItemAtPosition<UserBGGViewHolder>(0, click()))
        onView(withId(R.id.rvListThingsBGG)).check(RecyclerViewItemCountAssertion(18))
    }


    @Test
    fun showInfoDetailThingBGGTest() {
        runBlocking {
            val findByThingId = thingBGGDao.findByThingId(110331)
            val thingIdModel = ThingBGGMapperBBDD().toModel(findByThingId)
            onView(withId(R.id.rvListUsersBGG)).perform(actionOnItemAtPosition<UserBGGViewHolder>(0, click()))
            onView(withId(R.id.rvListThingsBGG)).perform(actionOnItemAtPosition<UserBGGViewHolder>(5, click()))
            onView(withId(R.id.tvName)).check(matches(withText(thingIdModel.nameFirst)))
            onView(withId(R.id.tvLanguageDependence)).check(matches(withText("Dependencia idioma: " + thingIdModel.getStringLanguageDependence())))
            onView(withId(R.id.tvLanguageDependence)).check(matches(withText(containsString(thingIdModel.getStringLanguageDependence()))))
        }
    }

}