package com.medicalapp.donorua.mvp.findregion.city

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.medicalapp.donorua.R
import com.medicalapp.donorua.utils.donorua.model.City
import com.medicalapp.donorua.utils.donorua.model.DonorCenterPreview


class CityListAdapter: RecyclerView.Adapter<CityViewHolder>() {

    private lateinit var listOfCities: List<City>
    private lateinit var onCLickedOnDonorCenterListener: (DonorCenterPreview) -> Unit

    fun set(list: List<City>, onCLickedListener: (DonorCenterPreview) -> Unit) {
        this.listOfCities = list
        this.onCLickedOnDonorCenterListener = onCLickedListener
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityViewHolder {
        return CityViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.city_item, parent, false)
        )
    }

    override fun getItemCount() = listOfCities.size

    override fun onBindViewHolder(holder: CityViewHolder, position: Int) {
        holder.bind(listOfCities[position], onCLickedOnDonorCenterListener)
    }
}