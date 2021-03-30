package com.medicalapp.donorua.mvp.findregion

import com.medicalapp.donorua.model.states.LoadingDataState
import com.medicalapp.donorua.utils.donorua.DonorUaApi
import com.medicalapp.donorua.utils.donorua.model.Region

interface IFindRegionContract {
    interface IFindRegionPresenter {
        fun loadData()
        fun onRegionClick(region: Region)
    }

    interface IFindRegionView {
        fun showRegions(list: List<Region>)
        fun moveUiToState(loadingDataState: LoadingDataState)
        fun getApiInstance() : DonorUaApi
    }
}