package com.medicalapp.donorua.ui.main.fragment.home

class HomePresenter(
    private val homeView: IHomeFragmentContract.IHomeFragmentView
): IHomeFragmentContract.IHomeFragmentPresenter {

    override fun onFindDonorClick() {

    }

    override fun onSaveACheckClick() {
        homeView.navigateToCaptureReceiptActivity()
    }

    override fun onInfoForDonorClick() {
        homeView.navigateToInfoActivity()
    }
}