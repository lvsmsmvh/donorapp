package com.medicalapp.donorua.mvp.main.fragment.home

interface IHomeFragmentContract {
    interface IHomeFragmentPresenter {
        fun onFindDonorClick()
    }

    interface IHomeFragmentView {
        fun navigateToFindCityActivity()
    }
}