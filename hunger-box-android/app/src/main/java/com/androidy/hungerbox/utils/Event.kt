package com.androidy.hungerbox.utils

import androidx.lifecycle.Observer


/**
 * Created by Ankit
 */

/**
 * Wrapper class for single event like Toast, SnackBar, Navigation events etc.
 *
 */

open class Event<out T>(private val content: T) {

    var hasBeenHandled = false
        private set

    fun getContentIfNotHandled(): T? {
        return if (hasBeenHandled)
            null
        else {
            hasBeenHandled = true
            content
        }
    }

    fun getPeekContent(): T = content
}

class EventObserver<T>(private val onEventUnhandledContent: (T) -> Unit): Observer<Event<T>> {

    override fun onChanged(t: Event<T>?) {
        t?.getContentIfNotHandled()?.let {
            onEventUnhandledContent(it)
        }
    }
}