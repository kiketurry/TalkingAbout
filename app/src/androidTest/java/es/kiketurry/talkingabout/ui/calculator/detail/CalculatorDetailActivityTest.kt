package es.kiketurry.talkingabout.ui.calculator.detail

import androidx.test.espresso.Espresso.onData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import es.kiketurry.talkingabout.R
import es.kiketurry.talkingabout.ui.calculator.supportclasses.ErrorEditTextMatcher
import es.kiketurry.talkingabout.ui.calculator.supportclasses.ToastMatcher
import org.hamcrest.CoreMatchers
import org.hamcrest.Matchers.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CalculatorDetailActivityTest {

    @get:Rule
    var activityScenarioRule: ActivityScenarioRule<CalculatorDetailActivity> = ActivityScenarioRule(CalculatorDetailActivity::class.java)

    @Test
    fun spinnerTest() {
        onView(withId(R.id.spDetail))
            .perform(click())

        onData(CoreMatchers.anything())
            .atPosition(1)
            .perform(click())

        onView(withId(R.id.spDetail))
            .check(matches(ViewMatchers.withSpinnerText(CoreMatchers.containsString("Manuel"))))
    }

    @Test
    fun spinnerTest2() {
        onView(withId(R.id.spDetail)).perform(click())
        onData(allOf(`is`(instanceOf(String::class.java)), `is`("Monica"))).perform(click())
        onView(withId(R.id.tvDetail)).check(matches(withText("Monica")))
    }

    @Test
    fun checkCustomMatcherUser() {
        onView(withId(R.id.etUser)).perform(typeText("asd"))
        onView(withId(R.id.btLogin)).perform(click())
        onView(withId(R.id.etUser)).check(matches(ErrorEditTextMatcher.withError(containsString("Tenemos un error en el username"))))
    }

    @Test
    fun checkCustomMatcherPassword() {
        onView(withId(R.id.etUser)).perform(typeText("Usuario"))
        onView(withId(R.id.etPassword)).perform(typeText("pas"))
        onView(withId(R.id.btLogin)).perform(click())
        onView(withId(R.id.etPassword)).check(matches(ErrorEditTextMatcher.withError(containsString("Tenemos un error en la password"))))
    }

    @Test
    fun checkToast() {
        onView(withId(R.id.btLogin)).perform(click())
        onView(withText("Testeando un toast")).inRoot(ToastMatcher()).check(matches(isDisplayed()))
    }
}