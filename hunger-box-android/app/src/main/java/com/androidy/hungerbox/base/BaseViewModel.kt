package com.androidy.hungerbox.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.androidy.hungerbox.utils.Event


/**
 * Created by Ankit
 */


open class BaseViewModel : ViewModel() {

    protected val _snackBarTextEvent by lazy { MutableLiveData<Event<Int>>() }
    val snackBarTextEvent: LiveData<Event<Int>> = _snackBarTextEvent

    protected val _isDataLoading by lazy { MutableLiveData<Boolean>() }
    val isDataLoading: LiveData<Boolean> = _isDataLoading
}