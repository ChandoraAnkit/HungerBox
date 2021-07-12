package com.androidy.hungerbox.commons.extensions

import android.content.Context
import android.view.LayoutInflater
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat


fun Context.inflater(): LayoutInflater =
        LayoutInflater.from(this)

fun Context?.getCompatDrawable(@DrawableRes resId: Int) =
        this?.let { ContextCompat.getDrawable(it, resId) }