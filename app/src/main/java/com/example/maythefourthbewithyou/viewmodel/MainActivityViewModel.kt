package com.example.maythefourthbewithyou.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.maythefourthbewithyou.network.repository.StarWarsRepository
import com.example.maythefourthbewithyou.network.response.PersonResponse
import com.example.maythefourthbewithyou.network.response.Result
import com.example.maythefourthbewithyou.util.Coroutines

/*
the view model for the main activity
 */
class MainActivityViewModel(application: Application): AndroidViewModel(application){
    private val repository = StarWarsRepository()
    private val _people = MutableLiveData<ArrayList<Result>>()
    private val peopleList = ArrayList<Result>()
    val people: LiveData<ArrayList<Result>> = _people
    private var page = ""
    private var isLast = false



    //getting the people and storing them in live data
    fun getPeople(){
        Coroutines.io {
            val response = repository.getPeople()
            page = response.body()!!.next
            peopleList.addAll(response.body()?.results!!)
            _people.postValue(peopleList)

        }
    }
    //getting the next page of charecters form the provided url in the current page
    fun getNextPage(){
        Coroutines.io {
            if (isLast) return@io
            //getting the page number from the url that is provided
            page = page.filter { it.isDigit() }
            val response = repository.getNextPage(page)
            //checking if the "next page" already has been loaded
            if (peopleList.containsAll(response.body()?.results!!)) {
                isLast = true
                return@io
            }
            peopleList.addAll(response.body()?.results!!)
            _people.postValue(peopleList)
            // need this because on the last page the "next" will be null
            if (response.body()!!.next == null) return@io
            //setting the next page
            page= response.body()?.next!!
        }
    }


}