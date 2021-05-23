package com.medicalapp.donorua.mvp.search.regions

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.medicalapp.donorua.R
import com.medicalapp.donorua.model.center.Region

class RegionListAdapter: RecyclerView.Adapter<RegionViewHolder>() {

    private lateinit var listOfRegions: List<Region>
    private lateinit var onCLickedListener: (Region) -> Unit

    fun set(listOfRegions: List<Region>, onCLickedListener: (onClickedRegion: Region) -> Unit) {
        this.listOfRegions = listOfRegions
        this.onCLickedListener = onCLickedListener
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RegionViewHolder {
        return RegionViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.region_item, parent, false)
        )
    }

    override fun getItemCount() = listOfRegions.size

    override fun onBindViewHolder(holder: RegionViewHolder, position: Int) {
        holder.bind(listOfRegions[position], onCLickedListener)
    }
}