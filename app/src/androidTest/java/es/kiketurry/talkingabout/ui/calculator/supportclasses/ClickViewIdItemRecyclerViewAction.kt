package es.kiketurry.talkingabout.ui.calculator.supportclasses

import android.view.View
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.matcher.ViewMatchers.isEnabled
import org.hamcrest.Matcher


class ClickViewIdItemRecyclerViewAction(private val viewId: Int) : ViewAction {
    override fun getConstraints(): Matcher<View> {
        return isEnabled()
    }

    override fun getDescription(): String {
        return "Click on a child view with specified id."
    }

    override fun perform(uiController: UiController?, view: View?) {
        val v: View = view!!.findViewById(viewId)
        v.performClick()
    }
}