package com.medicalapp.donorua.ui.main.fragment.centers

interface ICentersFragmentContract {
    interface ICentersPresenter {
        fun onFindCentersClick()
    }

    interface ICentersView {
        fun openFindCentersActivity()
        fun makeToast(text: String)
    }
}