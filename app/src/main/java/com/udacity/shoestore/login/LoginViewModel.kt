package com.udacity.shoestore.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel() {
    val loggedIn : LiveData<Boolean>
        get() = _loggedIn

    private val _loggedIn : MutableLiveData<Boolean> = MutableLiveData()

    init {
        _loggedIn.value = false
    }

    fun login(){
        _loggedIn.value = true
    }
}