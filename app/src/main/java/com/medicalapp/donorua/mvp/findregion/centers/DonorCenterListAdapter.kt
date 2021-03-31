package com.medicalapp.donorua.mvp.findregion.centers

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.medicalapp.donorua.R
import com.medicalapp.donorua.utils.donorua.model.DonorCenterPreview

class DonorCenterListAdapter: RecyclerView.Adapter<DonorCenterViewHolder>() {

    private lateinit var listOfCenters: List<DonorCenterPreview>
    private lateinit var onCLickedOnDonorCenterListener: (DonorCenterPreview) -> Unit

    fun set(list: List<DonorCenterPreview>, onCLickedListener: (DonorCenterPreview) -> Unit) {
        this.listOfCenters = list
        this.onCLickedOnDonorCenterListener = onCLickedListener
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DonorCenterViewHolder {
        return DonorCenterViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.donor_center_preview_item, parent, false)
        )
    }

    override fun getItemCount() = listOfCenters.size

    override fun onBindViewHolder(holder: DonorCenterViewHolder, position: Int) {
        holder.bind(listOfCenters[position], onCLickedOnDonorCenterListener)
    }
}