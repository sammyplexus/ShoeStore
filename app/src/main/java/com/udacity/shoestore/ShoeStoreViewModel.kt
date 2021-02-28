package com.udacity.shoestore

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Shoe

class ShoeStoreViewModel : ViewModel() {

    val shoeList : LiveData<List<Shoe>>
        get() = _shoeList

    private val _shoeList : MutableLiveData<List<Shoe>> = MutableLiveData()

    val loggedIn : LiveData<Boolean>
        get() = _isLoggedIn

    private val _isLoggedIn : MutableLiveData<Boolean> = MutableLiveData()


    init {
        _shoeList.value = getDefaultShoeList()
        _isLoggedIn.value = false
    }

    private fun getDefaultShoeList() : List<Shoe> {
        val shoeList = arrayListOf<Shoe>()
        shoeList.add(Shoe("Clarks", 12.0, "1 Clarkers", "Fine shoes",  listOf("Clarkers")))
        shoeList.add(Shoe("B Clarks", 10.0, "2 Clarkers", "Very Fine shoes",  listOf("FineClarkers")))
        shoeList.add(Shoe("C Clarks", 14.0, "3 Clarkers", "Very very Fine shoes",  listOf("Finest FineClarkers")))

        return shoeList
    }

    fun loggedInComplete() {
        _isLoggedIn.value = false
    }

    fun logIn(){
        _isLoggedIn.value = true
    }
}