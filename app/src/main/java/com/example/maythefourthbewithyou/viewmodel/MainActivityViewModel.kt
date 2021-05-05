package com.example.maythefourthbewithyou.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.maythefourthbewithyou.network.repository.StarWarsRepository
import com.example.maythefourthbewithyou.network.response.PersonResponse
import com.example.maythefourthbewithyou.util.Coroutines

/*
the view model for the main activity
 */
class MainActivityViewModel(application: Application): AndroidViewModel(application){
    private val repository = StarWarsRepository()
    private val _people = MutableLiveData<PersonResponse>()
    val people: LiveData<PersonResponse> = _people

    //getting the people and storing them in live data
    fun getPeople(){
        Coroutines.io {
            val response = repository.getPeople()
            _people.postValue(response.body())

        }
    }


}