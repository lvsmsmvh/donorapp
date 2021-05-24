package com.medicalapp.donorua.ui.main.fragment.home

interface IHomeFragmentContract {
    interface IHomeFragmentPresenter {
        fun onInfoForDonorClick()
    }

    interface IHomeFragmentView {
        fun navigateToInfoActivity()
    }
}