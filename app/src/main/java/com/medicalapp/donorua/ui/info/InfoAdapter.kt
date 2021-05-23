package com.medicalapp.donorua.ui.info

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.medicalapp.donorua.R
import kotlinx.android.synthetic.main.info_preview_item.view.*


class InfoModel(val infoEnum: InfoEnum, val onClickListener: View.OnClickListener)

class InfoAdapter(
    val list: List<InfoModel>
    ) : RecyclerView.Adapter<InfoAdapter.InfoViewHolder>() {


    init {
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InfoViewHolder {
        return InfoViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.info_preview_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: InfoViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount() = list.size

    class InfoViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        fun bind(infoModel: InfoModel) {
            view.apply {
                info_title.text = context.getString(infoModel.infoEnum.titleResId)
                info_item_card_view.setOnClickListener(infoModel.onClickListener)
                info_image.setImageDrawable(ContextCompat.getDrawable(context, infoModel.infoEnum.imgResId))
            }
        }
    }
}