package com.medicalapp.donorua.mvp.search.centers

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.medicalapp.donorua.model.center.DonorCenter
import kotlinx.android.synthetic.main.item_center.view.*

class CenterViewHolder(private val centerView: View) : RecyclerView.ViewHolder(centerView) {

    fun bind(center: DonorCenter, onCLickedListener: (DonorCenter) -> Unit) {
        centerView.apply {
            item_center_card_view.setOnClickListener {
                onCLickedListener(center)
            }

            item_center_center_name.text = center.name

            item_center_center_address.text = center.address
        }
    }

}