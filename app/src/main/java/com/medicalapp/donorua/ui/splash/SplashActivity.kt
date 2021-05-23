package com.medicalapp.donorua.ui.splash

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.medicalapp.donorua.R
import com.medicalapp.donorua.ui.main.MainActivity
import com.medicalapp.donorua.utils.extensions.simpleNavigateAndFinishAfter

class SplashActivity : AppCompatActivity(), ISplashContract.ISplashView {

    private lateinit var splashPresenter: SplashPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        splashPresenter = SplashPresenter(this)
    }

    override fun navigateToMainActivity() =
        simpleNavigateAndFinishAfter(MainActivity::class.java)
}