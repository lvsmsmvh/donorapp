package com.medicalapp.donorua.ui.main.fragment.checks

import android.net.Uri
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.medicalapp.donorua.model.check.Check
import com.medicalapp.donorua.utils.extensions.*
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_check.view.*

class CheckViewHolder(
    private val view: View
    ) : RecyclerView.ViewHolder(view) {
    fun bind(check: Check, onCLickedListener: (Check) -> Unit) {
        view.apply {
            item_check_card_view.setOnClickListener {
                onCLickedListener(check)
            }

            item_check_text_date.text = "Дата: " + check.dateAndTime.getDateInStringFormat()
            item_check_text_time.text = "Час: " + check.dateAndTime.getTimeInStringFormat()

            item_check_text_donor_center.apply {
                if (check.center != null) {
                    text = "Центр: " + check.center!!.name
                    show()
                } else hide()
            }

            Log.i("tag_uri", "Uri holder = " + check.uriOnImage)
            val uri = Uri.parse(check.uriOnImage)

            Picasso.get().load(uri)
                .fit().centerInside().into(item_check_image)

//            if (uri.scheme == "file")
//                Picasso.get().load(uri)
//                    .fit().centerInside().into(item_check_image)
//            else
//                Picasso.get().load(uri.toFileTryTwo(view.context))
//                    .fit().centerInside().into(item_check_image)

        }
    }
}