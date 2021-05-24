package com.medicalapp.donorua.ui.center

import android.content.Intent
import android.graphics.Paint
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.medicalapp.donorua.R
import com.medicalapp.donorua.model.center.DonorCenter
import com.medicalapp.donorua.utils.extensions.centersStorage
import com.medicalapp.donorua.utils.extensions.hide
import kotlinx.android.synthetic.main.activity_center.*


class CenterActivity: AppCompatActivity(),
    ICenterContract.ICenterView {

    private val isAddedToFav
        get() = centersStorage().favoriteCenters.isFavorite(donorCenter)

    private lateinit var presenter: CenterPresenter
    private lateinit var donorCenter: DonorCenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_center)

        presenter = CenterPresenter(this)
        donorCenter = centersStorage().getCenterById(id = intent.extras!!.getInt(KEY_CENTER_ID))

        with(donorCenter) {
            activity_center_name.text = name!!

            setFacebookInfo(facebook)

            setInstagramInfo(instagram)

            setInfoLinkItem(activity_center_address, address) {
                openUrl(linkOnGoogleMaps)
            }
            setInfoLinkItem(activity_center_mail, email) {
                openMail(email)
            }

            setInfoLinkItem(activity_center_link, webPageUrl) {
                openUrl(webPageUrl)
            }

            setInfoLinkItem(activity_center_donorua_link, urlInDonorUa) {
                openUrl(urlInDonorUa)
            }

            setDescription(description)
        }

        Log.i("tag_fav", centersStorage().favoriteCenters.getList().map { it.id }.toString())
    }

    private fun setInfoLinkItem(tv: TextView, text: String?, onClickListener: View.OnClickListener?) {
        if (text == null) {
            tv.hide()
            return
        }

        tv.text = text
        tv.isClickable = true
        tv.isFocusable = true
        tv.setOnClickListener(onClickListener)
        tv.paintFlags = tv.paintFlags or Paint.UNDERLINE_TEXT_FLAG
    }


    private fun setFacebookInfo(facebookUrl: String?) {
        if (facebookUrl == null)
            activity_center_icon_facebook.hide()
        else
            activity_center_icon_facebook.setOnClickListener {
                openUrl(facebookUrl)
            }
    }

    private fun setInstagramInfo(instagramUrl: String?) {
        if (instagramUrl == null)
            activity_center_icon_instagram.hide()
        else
            activity_center_icon_instagram.setOnClickListener {
                openUrl(instagramUrl)
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

    private fun openMail(address: String?) {
        if (address == null) {
            Toast.makeText(this, "Не валідне посилання", Toast.LENGTH_SHORT).show()
            return
        }
        startActivity(
            Intent(Intent.ACTION_SENDTO).apply { data = Uri.parse("mailto:" + address) }
        )
    }
    private fun openUrl(link: String?) {
        if (link == null) {
            Toast.makeText(this, "Не валідне посилання", Toast.LENGTH_SHORT).show()
            return
        }
        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(link)))
    }


    // menu

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.donor_center_menu, menu)
        updateMenuStarIcon(menu?.findItem(R.id.donor_center_menu_star))
        return true
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        centersStorage().favoriteCenters.changeExistingOfACenter(donorCenter)
        updateMenuStarIcon(item)
        return super.onOptionsItemSelected(item)
    }



    private fun updateMenuStarIcon(item: MenuItem?) {
        val newIconDrawableResId = if (isAddedToFav) R.drawable.ic_star_filled else R.drawable.ic_star
        item?.icon = ContextCompat.getDrawable(this, newIconDrawableResId)
    }

    companion object {
        const val KEY_CENTER_ID = "center_id"
    }
}

