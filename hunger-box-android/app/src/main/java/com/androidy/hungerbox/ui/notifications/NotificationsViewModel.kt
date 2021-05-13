package com.androidy.hungerbox.ui.notifications

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.androidy.hungerbox.base.BaseViewModel
import javax.inject.Inject

class NotificationsViewModel @Inject constructor()
    : BaseViewModel() {


    private val _text = MutableLiveData<String>().apply {
        value = "This is notifications Fragment"
    }
    val text: LiveData<String> = _text
}