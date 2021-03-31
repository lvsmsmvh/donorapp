package com.medicalapp.donorua.mvp.findregion.centers

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.medicalapp.donorua.utils.donorua.model.DonorCenterPreview
import kotlinx.android.synthetic.main.donor_center_preview_item.view.*

class DonorCenterViewHolder(private val centerView: View) : RecyclerView.ViewHolder(centerView) {
    fun bind(donorCenterPreview: DonorCenterPreview, onCLickedListener: (DonorCenterPreview) -> Unit) {
        centerView.apply {
            donor_center_name.text = donorCenterPreview.name
            donor_center_address.text = donorCenterPreview.address

            donor_center_item_card_view.setOnClickListener {
                onCLickedListener(donorCenterPreview)
            }
        }
    }
}