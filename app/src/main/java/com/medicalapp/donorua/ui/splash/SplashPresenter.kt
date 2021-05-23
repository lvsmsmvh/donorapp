package com.medicalapp.donorua.ui.splash

import android.os.CountDownTimer

class SplashPresenter(
    private val splashView: ISplashContract.ISplashView,
) : ISplashContract.ISplashPresenter
{

    val timeWaitMs = 1000L      // 1s

    init {
        object : CountDownTimer(timeWaitMs, timeWaitMs) {
            override fun onTick(millisUntilFinished: Long) {}

            override fun onFinish() { splashView.navigateToMainActivity() }
        }.start()
    }
}