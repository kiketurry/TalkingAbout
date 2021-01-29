package es.kiketurry.talkingabout.ui.calculator

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import es.kiketurry.talkingabout.R
import org.hamcrest.CoreMatchers.allOf
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CalculatorActivityTest {

    @get:Rule
    var activityScenarioRule: ActivityScenarioRule<CalculatorActivity> = ActivityScenarioRule(CalculatorActivity::class.java)

//    @get:Rule
//    var activityScenarioRule: IntentsTestRule<CalculatorActivity> = IntentsTestRule(CalculatorActivity::class.java)

    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        Assert.assertEquals("es.kiketurry.talkingabout", appContext.packageName)
    }

    @Test
    fun checkIsDisplayedOperations() {
        onView(withId(R.id.rbAdd)).check(matches(isDisplayed()))
        onView(withId(R.id.rbSubtract)).check(matches(isDisplayed()))
        onView(withId(R.id.rbMultiply)).check(matches(isDisplayed()))
        onView(withId(R.id.rbDivide)).check(matches(isDisplayed()))
    }

    @Test
    fun checkAdd() {
        onView(withId(R.id.etOperatorOne))
        onView(withHint(R.string.operator))
        //Para obtener seguro el segundo oerador con dos comprobaciones.
        onView(allOf(withId(R.id.etOperatorTwo), withHint(R.string.operator)))

        onView(withId(R.id.etOperatorOne))
            .perform(typeText("8"))
        onView(withId(R.id.etOperatorTwo))
            .perform(typeText("3"), ViewActions.closeSoftKeyboard())

        onView(withId(R.id.rbAdd))
            .perform(click())
        onView(withId(R.id.tvResult)).check(matches(withText("11.0")))
    }

    @Test
    fun checkSubtract() {
        onView(withId(R.id.etOperatorOne))
        onView(withHint(R.string.operator))
        //Para obtener seguro el segundo oerador con dos comprobaciones.
        onView(allOf(withId(R.id.etOperatorTwo), withHint(R.string.operator)))

        onView(withId(R.id.etOperatorOne))
            .perform(typeText("8"))
        onView(withId(R.id.etOperatorTwo))
            .perform(typeText("3"), ViewActions.closeSoftKeyboard())

        onView(withId(R.id.rbSubtract))
            .perform(click())
        onView(withId(R.id.tvResult)).check(matches(withText("5.0")))
    }

    @Test
    fun checkMultiply() {
        onView(withId(R.id.etOperatorOne))
        onView(withHint(R.string.operator))
        //Para obtener seguro el segundo oerador con dos comprobaciones.
        onView(allOf(withId(R.id.etOperatorTwo), withHint(R.string.operator)))

        onView(withId(R.id.etOperatorOne))
            .perform(typeText("8"))
        onView(withId(R.id.etOperatorTwo))
            .perform(typeText("3"), ViewActions.closeSoftKeyboard())

        onView(withId(R.id.rbMultiply))
            .perform(click())
        onView(withId(R.id.tvResult)).check(matches(withText("24.0")))
    }

    @Test
    fun checkDivide() {
        onView(withId(R.id.etOperatorOne))
        onView(withHint(R.string.operator))
        //Para obtener seguro el segundo oerador con dos comprobaciones.
        onView(allOf(withId(R.id.etOperatorTwo), withHint(R.string.operator)))

        onView(withId(R.id.etOperatorOne))
            .perform(typeText("8"))
        onView(withId(R.id.etOperatorTwo))
            .perform(typeText("3"), ViewActions.closeSoftKeyboard())

        onView(withId(R.id.rbDivide))
            .perform(click())
        onView(withId(R.id.tvResult)).check(matches(withText((8.0f / 3.0f).toString())))
    }

//    @Test
//    fun intentTest() {
//        intended(hasComponent(MainActivity::class.qualifiedName))
//    }

}