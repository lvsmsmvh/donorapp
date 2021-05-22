package com.medicalapp.donorua.mvp.main.fragment.home

interface IHomeFragmentContract {
    interface IHomeFragmentPresenter {
        fun onFindDonorClick()
        fun onInfoForDonorClick()
        fun onSaveACheckClick()
    }

    interface IHomeFragmentView {
        fun navigateToFindCityActivity()
        fun navigateToCaptureReceiptActivity()
        fun navigateToInfoActivity()
    }
}