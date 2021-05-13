package com.androidy.hungerbox.ui.feeds

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.androidy.hungerbox.base.BaseViewModel
import javax.inject.Inject

class FeedsViewModel @Inject constructor()
    : BaseViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text
}