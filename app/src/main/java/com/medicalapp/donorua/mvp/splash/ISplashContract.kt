package com.medicalapp.donorua.mvp.splash

interface ISplashContract {
    interface ISplashPresenter

    interface ISplashView {
        fun navigateToMainActivity()
//        fun navigateToFirstTimeActivity()
    }
}