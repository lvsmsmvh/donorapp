package com.medicalapp.donorua.mvp.main.fragment.home

interface IHomeFragmentContract {
    interface IHomeFragmentPresenter {
        fun onFindDonorClick()
        fun onCaptureReceiptClick()
        fun onInfoForDonorClick()
    }

    interface IHomeFragmentView {
        fun navigateToFindCityActivity()
        fun navigateToCaptureReceiptActivity()
        fun navigateToInfoActivity()
    }
}