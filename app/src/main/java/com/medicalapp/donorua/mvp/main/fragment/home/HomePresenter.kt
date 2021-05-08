package com.medicalapp.donorua.mvp.main.fragment.home

class HomePresenter(
    private val homeView: IHomeFragmentContract.IHomeFragmentView
): IHomeFragmentContract.IHomeFragmentPresenter {

    override fun onFindDonorClick() {
        homeView.navigateToFindCityActivity()
    }

    override fun onCaptureReceiptClick() {
        homeView.navigateToCaptureReceiptActivity()
    }

    override fun onInfoForDonorClick() {
        homeView.navigateToInfoActivity()
    }
}