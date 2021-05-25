package com.medicalapp.donorua.ui.check.fullscreen

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.medicalapp.donorua.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_fullscreen_image.*

class FullScreenImageActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fullscreen_image)

        val uri = intent.data!!
//
        Picasso.get().load(uri).rotate(90f).fit().centerInside()
            .into(activity_fullscreen_container)
    }
}