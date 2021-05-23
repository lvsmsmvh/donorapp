package com.medicalapp.donorua.ui.center

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class CenterActivity: AppCompatActivity(),
    ICenterContract.ICenterView {

    lateinit var presenter: CenterPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        presenter = CenterPresenter(this)
    }




}