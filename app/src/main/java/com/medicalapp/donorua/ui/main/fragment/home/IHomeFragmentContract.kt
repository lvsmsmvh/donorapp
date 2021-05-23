package com.medicalapp.donorua.ui.main.fragment.home

interface IHomeFragmentContract {
    interface IHomeFragmentPresenter {
        fun onFindDonorClick()
        fun onInfoForDonorClick()
        fun onSaveACheckClick()
    }

    interface IHomeFragmentView {
        fun navigateToCaptureReceiptActivity()
        fun navigateToInfoActivity()
    }
}