package com.udacity.shoestore

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Shoe

class ShoeStoreViewModel : ViewModel() {

    val shoeList : LiveData<ArrayList<Shoe>>
        get() = _shoeList

    val navigateToShoeList : LiveData<Boolean>
        get() = _navigateToShoeList

    private val _navigateToShoeList : MutableLiveData<Boolean> = MutableLiveData()

    private val _shoeList : MutableLiveData<ArrayList<Shoe>> = MutableLiveData()

    val loggedIn : LiveData<Boolean>
        get() = _isLoggedIn

    private val _isLoggedIn : MutableLiveData<Boolean> = MutableLiveData()


    init {
        _shoeList.value = getDefaultShoeList()
        _navigateToShoeList.value = false
        _isLoggedIn.value = false
    }

    private fun getDefaultShoeList() : ArrayList<Shoe> {
        val shoeList = arrayListOf<Shoe>()
        shoeList.add(Shoe("Clarks", 12.0, "Clarks inc", "Clark's classic official shoes",  listOf()))
        shoeList.add(Shoe("Oxfords", 10.0, "Oxfords inc", "Oxfords casual wear shoes",  listOf()))
        shoeList.add(Shoe("Brogues", 14.0, "Brogues inc", "Brogues toughened to withstand bad weather",  listOf()))

        return shoeList
    }

    fun logIn(){
        _isLoggedIn.value = true
    }

    fun addShoe(shoe: Shoe?) {
        shoe?.let {
            val listOfShoes = _shoeList.value
            listOfShoes?.add(shoe)

            _shoeList.value = listOfShoes

            navigateToShoeList()
        }
    }

    private fun navigateToShoeList(){
        _navigateToShoeList.value = true
    }

    fun logOut() {
        _isLoggedIn.value = false
    }

    fun navigateToShoeListComplete() {
        _navigateToShoeList.value = false
    }
}