package com.medicalapp.donorua.mvp.main.fragment.home

import com.medicalapp.donorua.utils.donorua.DonorUaApi

class HomePresenter(
    private val homeView: IHomeFragmentContract.IHomeFragmentView
): IHomeFragmentContract.IHomeFragmentPresenter {

    override fun onFindDonorClick() {
        homeView.navigateToFindCityActivity()
    }

    override fun onSaveACheckClick() {
        homeView.navigateToCaptureReceiptActivity()
    }

    override fun onInfoForDonorClick() {
        homeView.navigateToInfoActivity()
    }
}