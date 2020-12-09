package es.kiketurry.talkingabout.extensions

import android.view.View

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