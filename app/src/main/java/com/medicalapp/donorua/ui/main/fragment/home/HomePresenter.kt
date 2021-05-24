package com.medicalapp.donorua.ui.main.fragment.home

class HomePresenter(
    private val homeView: IHomeFragmentContract.IHomeFragmentView
): IHomeFragmentContract.IHomeFragmentPresenter {

    override fun onInfoForDonorClick() {
        homeView.navigateToInfoActivity()
    }
}