package es.kiketurry.talkingabout.extensions

import android.content.Context
import android.view.View
import android.widget.Toast
import es.kiketurry.talkingabout.ui.base.BaseActivity
import es.kiketurry.talkingabout.ui.base.BaseFragment

fun View.visible() {
    visibility = View.VISIBLE
}

fun View.invisible() {
    visibility = View.INVISIBLE
}

fun View.gone() {
    visibility = View.GONE
}

fun View.visible(show: Boolean) {
    if (show) {
        visible()
    } else {
        gone()
    }
}

fun Context.toast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun BaseActivity<*>.toast(message: String) {
    baseContext.toast(message)
}

fun BaseFragment<*>.toast(message: String) {
    context?.toast(message)
}