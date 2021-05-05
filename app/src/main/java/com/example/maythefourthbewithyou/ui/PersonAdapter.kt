package com.example.maythefourthbewithyou.ui

import android.app.Application
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.maythefourthbewithyou.databinding.PersonItemLayoutBinding
import com.example.maythefourthbewithyou.network.response.Result

/*
*   the adapter to display the people in the recycler view
 */
class PersonAdapter(var dataSet: List<Result>): RecyclerView.Adapter<PersonAdapter.PersonViewHolder>() {
    inner class PersonViewHolder(val binding: PersonItemLayoutBinding): RecyclerView.ViewHolder(binding.root) {

        private val personName: TextView = binding.tvName
        private val personHeight: TextView = binding.tvHeight
        private val personMass: TextView = binding.tvMass
        private val personDOB: TextView = binding.tvBirthYear

        fun onBind(data: Result){
            personName.text = data.name
            personHeight.text = data.height
            personMass.text = data.mass
            personDOB.text = data.birth_year

            //click listener for when a item is clicked on
            binding.root.setOnClickListener {
                Toast.makeText(it.context, data.eye_color, Toast.LENGTH_SHORT).show()
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonViewHolder {
        val binding = PersonItemLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return PersonViewHolder(binding)
    }

    override fun getItemCount() = dataSet.size

    override fun onBindViewHolder(holder: PersonViewHolder, position: Int) {
        holder.onBind(dataSet[position])
    }
}