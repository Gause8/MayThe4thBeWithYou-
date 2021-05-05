package com.example.maythefourthbewithyou.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.example.maythefourthbewithyou.R
import com.example.maythefourthbewithyou.databinding.ActivityMainBinding
import com.example.maythefourthbewithyou.viewmodel.MainActivityViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var recyclerView: RecyclerView
    private val viewModel: MainActivityViewModel by viewModels<MainActivityViewModel>()
    private val personAdapter: PersonAdapter = PersonAdapter(mutableListOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //setting up view binding, the view model, the recycler view
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        binding.viewmodel = viewModel
        recyclerView = binding.rvPersonList
        recyclerView.adapter = personAdapter

        //requesting the viewmodel for the list of people
        viewModel.getPeople()
        // observing the people live data
        viewModel.people.observe(this, Observer {
            //passing that data along to the adapter
            personAdapter.dataSet = it.results
            personAdapter.notifyDataSetChanged()
        })

    }
}