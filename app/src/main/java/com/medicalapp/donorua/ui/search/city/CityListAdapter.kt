package com.medicalapp.donorua.ui.search.city

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.medicalapp.donorua.R
import com.medicalapp.donorua.model.center.City

class CityListAdapter: RecyclerView.Adapter<CityViewHolder>() {

    private lateinit var listOfCities: List<City>
    private lateinit var onCLickedListener: (City) -> Unit

    fun set(listOfCities: List<City>, onCLickedListener: (onClickedCity: City) -> Unit) {
        this.listOfCities = listOfCities
        this.onCLickedListener = onCLickedListener
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityViewHolder {
        return CityViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_city, parent, false)
        )
    }

    override fun getItemCount() = listOfCities.size

    override fun onBindViewHolder(holder: CityViewHolder, position: Int) {
        holder.bind(listOfCities[position], onCLickedListener)
    }
}