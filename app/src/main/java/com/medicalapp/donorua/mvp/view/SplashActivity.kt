package com.medicalapp.donorua.mvp.view

import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.medicalapp.donorua.R
import com.medicalapp.donorua.mvp.contract.ISplashContract
import com.medicalapp.donorua.mvp.presenter.SplashPresenter
import com.medicalapp.donorua.utils.LogTags
import com.medicalapp.donorua.utils.extensions.simpleNavigate
import com.medicalapp.donorua.utils.extensions.simpleNavigateAndFinishAfter

class SplashActivity : AppCompatActivity(), ISplashContract.ISplashView {

    private lateinit var splashPresenter: SplashPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_activity)

        splashPresenter = SplashPresenter(this)
    }

    override fun navigateToMainActivity() =
        simpleNavigateAndFinishAfter(MainActivity::class.java)

    override fun navigateToFirstTimeActivity() =
        simpleNavigateAndFinishAfter(FirstOpenActivity::class.java)
}