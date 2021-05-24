package com.medicalapp.donorua.ui.center

import android.os.Bundle
import android.text.BoringLayout
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.marginLeft
import com.google.mlkit.vision.text.Text
import com.medicalapp.donorua.R
import com.medicalapp.donorua.model.center.DonorCenter
import com.medicalapp.donorua.utils.extensions.centersStorage
import com.medicalapp.donorua.utils.extensions.hide
import com.medicalapp.donorua.utils.extensions.toDp
import kotlinx.android.synthetic.main.activity_center.*

class CenterActivity: AppCompatActivity(),
    ICenterContract.ICenterView {

    private lateinit var presenter: CenterPresenter
    private lateinit var donorCenter: DonorCenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_center)

        presenter = CenterPresenter(this)
        donorCenter = centersStorage().getCenterById(id = intent.extras!!.getInt(KEY_CENTER_ID))

        with(donorCenter) {

            // TODO set name

            // TODO set address

            // TODO fix icons to info items

            // TODO fix info items positioning


            setFacebookInfo(facebook)

            setInstagramInfo(instagram)

            email?.let { addInfoItemPiece(R.drawable.ic_mail, email) {
                // TODO open mail
            } }

            webPageUrl?.let { addInfoItemPiece(R.drawable.ic_link, webPageUrl) {
                // TODO open web site
            } }

            urlInDonorUa?.let { addInfoItemPiece(R.drawable.ic_flower, urlInDonorUa) {
                // TODO url donor ua
            } }

            setDescription(description)
        }
    }


    private fun addInfoItemPiece(
        drawableRes: Int,
        text: String,
        onClickListener: View.OnClickListener? = null
    ) {
        val drawable = ContextCompat.getDrawable(this, drawableRes)

        val textView = layoutInflater.inflate(R.layout.item_info_in_center,
            null) as TextView

        textView.apply {
            this.text = text
            this.setOnClickListener(onClickListener)
            setCompoundDrawables(drawable, null, null, null)
        }

        activity_center_info_pieces_container.addView(textView)
    }


    private fun setFacebookInfo(facebookUrl: String?) {
        if (facebookUrl == null)
            activity_center_icon_facebook.hide()
        else
            activity_center_icon_facebook.setOnClickListener {
                // TODO open facebook
            }
    }

    private fun setInstagramInfo(instagramUrl: String?) {
        if (instagramUrl == null)
            activity_center_icon_instagram.hide()
        else
            activity_center_icon_facebook.setOnClickListener {
                // TODO open instagram
            }
    }

    private fun setDescription(list: List<String>?) {
        if (list.isNullOrEmpty()) {
            activity_center_description.hide()
            return
        }

        var description = ""
        list.forEach { text  ->
            description += text
            if (list.last() != text)
                description += "\n\n"
        }

        activity_center_description.text = description
    }

    companion object {
        const val KEY_CENTER_ID = "center_id"
    }
}

