package com.androidy.hungerbox.core.di

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

interface BaseComponent<T> {
    fun inject(target: T)
}


interface BaseActivityComponent<T: AppCompatActivity>: BaseComponent<T>


interface BaseFragmentComponent<T: Fragment>: BaseComponent<T>