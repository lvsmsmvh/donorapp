package com.medicalapp.donorua.ui.search.centers

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.medicalapp.donorua.R
import com.medicalapp.donorua.model.center.DonorCenter

class CenterListAdapter: RecyclerView.Adapter<CenterViewHolder>() {

    private lateinit var listOfCenter: List<DonorCenter>
    private lateinit var onCLickedListener: (DonorCenter) -> Unit

    fun set(listOfCenters: List<DonorCenter>, onCLickedListener: (onClickedCity: DonorCenter) -> Unit) {
        this.listOfCenter = listOfCenters
        this.onCLickedListener = onCLickedListener
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CenterViewHolder {
        return CenterViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_center, parent, false)
        )
    }

    override fun getItemCount() = listOfCenter.size

    override fun onBindViewHolder(holder: CenterViewHolder, position: Int) {
        holder.bind(listOfCenter[position], onCLickedListener)
    }
}