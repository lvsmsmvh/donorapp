package com.medicalapp.donorua.mvp.splash

import android.os.CountDownTimer
import com.medicalapp.donorua.App

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
        sharedPrefsHelper.getUser()?.let {
            splashView.navigateToMainActivity()
            return
        }
        splashView.navigateToFirstTimeActivity()
    }
}