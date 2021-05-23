package com.medicalapp.donorua.mvp.main.fragment.centers

interface ICentersFragmentContract {
    interface ICentersPresenter {
        fun onFindCentersClick()
    }

    interface ICentersView {
        fun openFindCentersActivity()
        fun makeToast(text: String)
    }
}