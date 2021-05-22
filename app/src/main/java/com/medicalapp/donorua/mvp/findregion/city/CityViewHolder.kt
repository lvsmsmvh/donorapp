package com.medicalapp.donorua.mvp.findregion.city

import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.medicalapp.donorua.mvp.findregion.centers.DonorCenterListAdapter
import com.medicalapp.donorua.model.center.City
import com.medicalapp.donorua.utils.donorua.model.DonorCenterPreview
import kotlinx.android.synthetic.main.city_item.view.*

class CityViewHolder(private val cityView: View) : RecyclerView.ViewHolder(cityView) {
    fun bind(city: City, onCLickedListener: (DonorCenterPreview) -> Unit) {
        cityView.apply {
            city_item_city_name.text = city.name
            city_item_recycler_view.layoutManager = LinearLayoutManager(cityView.context)

            val adapter = DonorCenterListAdapter()
//            adapter.set(city.centers, onCLickedListener)
            city_item_recycler_view.adapter = adapter
        }
    }
}