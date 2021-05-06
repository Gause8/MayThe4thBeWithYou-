package com.example.maythefourthbewithyou.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.example.maythefourthbewithyou.R
import com.example.maythefourthbewithyou.databinding.ActivityMainBinding
import com.example.maythefourthbewithyou.viewmodel.MainActivityViewModel

class MainActivity : AppCompatActivity(), PersonAdapter.GetData {
    private lateinit var binding: ActivityMainBinding
    private lateinit var recyclerView: RecyclerView
    private val viewModel: MainActivityViewModel by viewModels<MainActivityViewModel>()
    private val personAdapter: PersonAdapter = PersonAdapter(mutableListOf(), this)
    private var isEnd = 0
    // the position of the recycler view doesnt always change so using this to check if it changes
    private var isChanged = false

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
            personAdapter.dataSet = it
            personAdapter.notifyDataSetChanged()
        })
        listenerOnScroll()


    }

    //setting up a onScrollListener for the recycler view
    private fun listenerOnScroll(){
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener(){
            //just testing this to see what the different states are
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (newState == RecyclerView.SCROLL_STATE_IDLE){
                    Log.d("scroll","idle")
                }else if(newState == RecyclerView.SCROLL_STATE_SETTLING){
                    Log.d("scroll","settling")
                }else if(newState == RecyclerView.SCROLL_STATE_DRAGGING){
                    Log.d("scroll","dragging")
                }
            }
            //this is to check if the recycler view is at the end of the list
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                //getting the total items in the recycler view
                val temp = recyclerView.layoutManager!!.itemCount
                // if its at the end of the list
                if (temp-1 == isEnd && isChanged){
                    Log.d("scroll","scrolling $temp")
                    viewModel.getNextPage()
                    isChanged = false

                }

            }
        })
    }
    // using this to get the position the recycler view is currently viewing
    override fun getNextPage(i: Int) {
        if (isEnd == i) isChanged = false
        else{
            isEnd = i
            isChanged = true
        }
    }
}
