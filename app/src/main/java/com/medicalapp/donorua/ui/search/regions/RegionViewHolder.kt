package com.medicalapp.donorua.ui.search.regions

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.medicalapp.donorua.model.center.Region
import com.medicalapp.donorua.utils.extensions.numberOfCenters
import kotlinx.android.synthetic.main.item_region.view.*

class RegionViewHolder(private val regionView: View) : RecyclerView.ViewHolder(regionView) {

    fun bind(region: Region, onCLickedListener: (Region) -> Unit) {
        regionView.apply {
            region_item_card_view.setOnClickListener {
                onCLickedListener(region)
            }

            region_item_region_name.text = region.name

            val amountOfCentersStr = "Кількість центрів: " + region.numberOfCenters().toString()
            region_item_number_of_centers.text = amountOfCentersStr
        }
    }

}