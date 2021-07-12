package com.androidy.hungerbox.commons.extensions

import android.app.ProgressDialog
import android.view.View
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.androidy.hungerbox.commonUi.R
import com.androidy.hungerbox.utils.Event
import com.google.android.material.snackbar.Snackbar


/**
 * Created by Ankit
 */


fun View.show() {
    this.visibility = View.VISIBLE
}

fun View.hide() {
    this.visibility = View.INVISIBLE
}

fun View.gone() {
    this.visibility = View.GONE
}

fun View.showSnackBar(message: String, time: Int) {
    Snackbar.make(this, message, time).show()
}

fun View.setUpSnackBar(lifeCycleOwner: LifecycleOwner,
                       snackBarEvent: LiveData<Event<Int>>,
                       time: Int = Snackbar.LENGTH_SHORT) {

    snackBarEvent.observe(lifeCycleOwner, Observer { event ->
        event.getContentIfNotHandled()?.let {
            showSnackBar(context.getString(it), time)
        }
    })
}

/**
 * Todo refactor later
 */
fun View?.showLoader(message: String) {
    this?.let {
        val pd = ProgressDialog(it.context)
        pd.setMessage(message)
        it.setTag(R.string.tag_dialog, pd)
        pd.show()
    }
}

/**
 * Todo refactor later
 */
fun View?.hideLoader() {
    this?.let{
        val loader = it.getTag(R.string.tag_dialog) as ProgressDialog?
        loader?.cancel()
    }
}

