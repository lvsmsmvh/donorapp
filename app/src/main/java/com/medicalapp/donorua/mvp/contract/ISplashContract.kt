package com.medicalapp.donorua.mvp.contract

interface ISplashContract {
    interface ISplashPresenter {
    }

    interface ISplashView {
        fun navigateToMainActivity()
        fun navigateToFirstTimeActivity()
    }
}