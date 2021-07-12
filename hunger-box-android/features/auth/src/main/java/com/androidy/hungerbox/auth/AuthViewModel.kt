package com.androidy.hungerbox.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.androidy.hungerbox.auth.data.source.LoginRegisterRepository
import com.androidy.hungerbox.core.data.Result
import com.androidy.hungerbox.utils.Event
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject


/**
 * Created by Ankit
 */


class AuthViewModel @Inject constructor(
        private val repository: LoginRegisterRepository
)
    : ViewModel() {

    val email = MutableLiveData<String>()
    val password = MutableLiveData<String>()

    private val _registerUserEvent = MutableLiveData<Event<Unit>>()
    val registerUserEvent: MutableLiveData<Event<Unit>> = _registerUserEvent

    private val _loginUserEvent = MutableLiveData<Event<Unit>>()
    val loginUserEvent: MutableLiveData<Event<Unit>> = _loginUserEvent

    private val _snackBarTextEvent by lazy { MutableLiveData<Event<Int>>() }
    val snackBarTextEvent: LiveData<Event<Int>> = _snackBarTextEvent

    private val _isDataLoading by lazy { MutableLiveData<Boolean>() }
    val isDataLoading: LiveData<Boolean> = _isDataLoading

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
                    _snackBarTextEvent.value = Event(R.string.msg_error_login)
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
                    _snackBarTextEvent.value = Event(R.string.msg_error_register)
                }
            }
            _isDataLoading.value = false
        }
    }
}