package com.medicalapp.donorua.ui.info

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.medicalapp.donorua.R
import kotlinx.android.synthetic.main.activity_info_details.*

class InfoDetailsActivity: AppCompatActivity() {

    companion object {
        const val keyIntentEnumName = "keyIntentEnumName"
        fun openActivity(context: Context, infoEnum: InfoEnum) {
            val intent = Intent(context, InfoDetailsActivity::class.java)
            intent.putExtra(keyIntentEnumName, infoEnum.name)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info_details)

        val enum = InfoEnum.valueOf(intent.getStringExtra(keyIntentEnumName)!!)

        activity_info_details_image.setImageDrawable(
            ContextCompat.getDrawable(this, enum.imgResId))

        activity_info_details_title.text =
            getString(enum.titleResId)

        activity_info_details_description.text =
            getString(enum.descriptionResId)
    }

}