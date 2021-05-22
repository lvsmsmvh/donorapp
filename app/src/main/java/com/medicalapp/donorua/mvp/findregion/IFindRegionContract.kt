package com.medicalapp.donorua.mvp.findregion

import com.medicalapp.donorua.model.findcenterstates.LoadingDataState
import com.medicalapp.donorua.model.findcenterstates.ShowingContentType
import com.medicalapp.donorua.utils.donorua.DonorUaApi
import com.medicalapp.donorua.model.center.City
import com.medicalapp.donorua.utils.donorua.model.DonorCenterPreview
import com.medicalapp.donorua.model.center.Region

interface IFindRegionContract {
    interface IFindRegionPresenter {
        fun onBackPressedClick()
        fun loadListOfRegions()
        fun loadListOfDonorCenters(region: Region)
        fun loadDonorCenter(donorCenterPreview: DonorCenterPreview)
        fun onRegionClick(region: Region)
        fun onDonorCenterPreviewClick(centerPreview: DonorCenterPreview)
    }

    interface IFindRegionView {
        fun exit()
        fun getCurrentShowingContentType(): ShowingContentType
        fun showRegions(list: List<Region>)
        fun showDonorCenters(list: List<City>)
        fun moveUiToNewLoadingState(loadingDataState: LoadingDataState)
        fun moveUiToNewContentType(showingContentType: ShowingContentType)
        fun getApiInstance() : DonorUaApi
    }
}