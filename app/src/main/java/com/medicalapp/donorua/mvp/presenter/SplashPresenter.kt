package com.medicalapp.donorua.mvp.presenter

import android.os.CountDownTimer
import com.medicalapp.donorua.App
import com.medicalapp.donorua.mvp.contract.ISplashContract

class SplashPresenter(
    private val splashView: ISplashContract.ISplashView,
) : ISplashContract.ISplashPresenter
{

    private val sharedPrefsHelper = App.instance.sharedPrefs

    init {
        object : CountDownTimer(2000, 100) {
            override fun onTick(millisUntilFinished: Long) {

            }

            override fun onFinish() {
                navigate()
            }
        }.start()
    }

    private fun navigate() {
        sharedPrefsHelper.getUser().let { user ->
            if (user == null)
                splashView.navigateToFirstTimeActivity()
            else if (user.placeGeo == null)
                splashView.navigateToMainActivity()
            else
                splashView.navigateToMainActivity()
        }
    }
}