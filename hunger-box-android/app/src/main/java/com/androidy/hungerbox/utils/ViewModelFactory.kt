package com.androidy.hungerbox.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException
import javax.inject.Inject
import javax.inject.Provider


/**
 * Created by Ankit
 */


class ViewModelFactory @Inject constructor (private val viewModelsMap: Map<Class<out ViewModel>,
        @JvmSuppressWildcards Provider<ViewModel>>)
    : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val creator = viewModelsMap[modelClass]
                ?: throw IllegalArgumentException("Unknown view model class $modelClass")

        return creator.get() as T
    }
}
