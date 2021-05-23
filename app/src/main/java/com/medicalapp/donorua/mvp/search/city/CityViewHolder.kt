package com.medicalapp.donorua.mvp.search.city

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.medicalapp.donorua.model.center.City
import com.medicalapp.donorua.utils.extensions.numberOfCenters
import kotlinx.android.synthetic.main.item_city.view.*

class CityViewHolder(private val cityView: View) : RecyclerView.ViewHolder(cityView) {

    fun bind(city: City, onCLickedListener: (City) -> Unit) {
        cityView.apply {
            city_item_card_view.setOnClickListener {
                onCLickedListener(city)
            }

            city_item_city_name.text = city.name

            val amountOfCentersStr = "Кількість центрів: " + city.centers.size.toString() + "  "+ city.numberOfCenters()
            city_item_number_of_centers.text = amountOfCentersStr
        }
    }

}