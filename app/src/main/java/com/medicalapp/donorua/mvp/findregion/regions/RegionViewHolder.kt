package com.medicalapp.donorua.mvp.findregion.regions

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.medicalapp.donorua.model.center.Region
import kotlinx.android.synthetic.main.region_item.view.*

class RegionViewHolder(private val regionView: View) : RecyclerView.ViewHolder(regionView) {
    fun bind(region: Region, onCLickedListener: (Region) -> Unit) {
        regionView.apply {
            region_item_card_view.setOnClickListener {
                onCLickedListener(region)
            }
            region_item_region_name.text = region.name
        }
    }
}