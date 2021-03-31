package com.medicalapp.donorua.mvp.main.fragment.home

class HomePresenter(
    private val homeView: IHomeFragmentContract.IHomeFragmentView
): IHomeFragmentContract.IHomeFragmentPresenter {

    override fun onFindDonorClick() {
        homeView.navigateToFindCityActivity()
    }
}