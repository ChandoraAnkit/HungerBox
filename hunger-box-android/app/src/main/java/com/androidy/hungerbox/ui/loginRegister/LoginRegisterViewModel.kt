package com.androidy.hungerbox.ui.loginRegister

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.androidy.hungerbox.R
import com.androidy.hungerbox.base.BaseViewModel
import com.androidy.hungerbox.data.Result
import com.androidy.hungerbox.data.loginRegister.source.LoginRegisterRepository
import com.androidy.hungerbox.utils.Event
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject


/**
 * Created by Ankit
 */


class LoginRegisterViewModel @Inject constructor(
        private val repository: LoginRegisterRepository)
    : BaseViewModel() {

    val email = MutableLiveData<String>()
    val password = MutableLiveData<String>()

    private val _registerUserEvent = MutableLiveData<Event<Unit>>()
    val registerUserEvent: MutableLiveData<Event<Unit>> = _registerUserEvent

    private val _loginUserEvent = MutableLiveData<Event<Unit>>()
    val loginUserEvent: MutableLiveData<Event<Unit>> = _loginUserEvent

    fun openRegisterPage() {
        _registerUserEvent.value = Event(Unit)
    }

    fun loginUser() {
        _isDataLoading.value = true

        val currentEmail = email.value
        val currentPass = password.value

        if (currentEmail == null || currentPass == null) {
            _snackBarTextEvent.value = Event(R.string.msg_mandatory_fields)
            _isDataLoading.value = false
            return
        }
        viewModelScope.launch {
            when (val user = repository.loginUser(currentEmail, currentPass)) {
                is Result.Success -> {
                     Timber.d("User Login => ${user.data}")
                    _loginUserEvent.value = Event(Unit)
                }
                is Result.Error ->{
                    Timber.d("User Login => ${user.exception.message}")
                    _snackBarTextEvent.value = Event(R.string.error_msg_login)
                }
            }
            _isDataLoading.value = false
        }
    }

    fun registerUser() {
        _isDataLoading.value = true
        val currentEmail = email.value
        val currentPass = password.value

        if (currentEmail == null || currentPass == null) {
            _snackBarTextEvent.value = Event(R.string.msg_mandatory_fields)
            _isDataLoading.value = false
            return
        }
        viewModelScope.launch {
            when (val user = repository.registerUser(currentEmail, currentPass)) {
                is Result.Success -> {
                    Timber.d("User Register => ${user.data}")
                    _registerUserEvent.value = Event(Unit)
                }
                is Result.Error ->{
                    Timber.d("User Register => ${user.exception.message}")
                    _snackBarTextEvent.value = Event(R.string.error_msg_register)
                }
            }
            _isDataLoading.value = false
        }
    }
}