package es.kiketurry.talkingabout.extensions

import android.content.Context
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import es.kiketurry.talkingabout.ui.base.BaseActivity
import es.kiketurry.talkingabout.ui.base.BaseFragment
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.conflate

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


//Pruebas de flow para el futuro
val View.onClickEvents: Flow<View>
    get() = callbackFlow {
        val onClickListener = View.OnClickListener { offer(it) }
        setOnClickListener(onClickListener)
        awaitClose { setOnClickListener(null) }
    }.conflate()

val RecyclerView.lastVisibleEvents: Flow<Int>
    get() = callbackFlow<Int> {
        val layoutManager = layoutManager as GridLayoutManager
        val listener = object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                offer(layoutManager.findLastVisibleItemPosition())
            }
        }

        addOnScrollListener(listener)
        awaitClose { removeOnScrollListener(listener) }
    }.conflate()