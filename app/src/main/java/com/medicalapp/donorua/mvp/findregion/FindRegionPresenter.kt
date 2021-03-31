package com.medicalapp.donorua.mvp.findregion

import com.medicalapp.donorua.model.findcenterstates.LoadingDataState
import com.medicalapp.donorua.model.findcenterstates.LoadingDataType
import com.medicalapp.donorua.model.findcenterstates.ShowingContentType
import com.medicalapp.donorua.utils.donorua.model.DonorCenterPreview
import com.medicalapp.donorua.utils.donorua.model.Region

class FindRegionPresenter(
    private val findRegionView: IFindRegionContract.IFindRegionView
    ) : IFindRegionContract.IFindRegionPresenter {

    private val donorUaApi = findRegionView.getApiInstance()
    private var currentLoadingDataType = LoadingDataType.NONE

    private fun finishLoadingData() {
        currentLoadingDataType = LoadingDataType.NONE
    }


    override fun onBackPressedClick() {
        if (currentLoadingDataType != LoadingDataType.NONE) {
            finishLoadingData()
            return
        }

        when (findRegionView.getCurrentShowingContentType()) {
            ShowingContentType.SINGLE_CENTER -> {
                findRegionView.moveUiToNewContentType(ShowingContentType.LIST_OF_CENTERS)
            }
            ShowingContentType.LIST_OF_CENTERS -> {
                findRegionView.moveUiToNewContentType(ShowingContentType.LIST_OF_REGIONS)
            }
            ShowingContentType.LIST_OF_REGIONS -> {
                findRegionView.exit()
            }
        }
    }

    override fun onRegionClick(region: Region) {
        loadListOfDonorCenters(region)
    }

    override fun onDonorCenterPreviewClick(centerPreview: DonorCenterPreview) {
        donorUaApi.openDonorPreviewInWeb(centerPreview)
    }


    override fun loadListOfRegions() {
        currentLoadingDataType = LoadingDataType.LIST_OF_REGIONS
        findRegionView.moveUiToNewLoadingState(LoadingDataState.Loading)
        donorUaApi.loadListOfRegions { listRegion ->
            if (currentLoadingDataType == LoadingDataType.LIST_OF_REGIONS) {
                if (listRegion.isEmpty()) {
                    findRegionView.moveUiToNewLoadingState(LoadingDataState.Failed)
                } else {
                    findRegionView.moveUiToNewLoadingState(LoadingDataState.Loaded)
                    findRegionView.moveUiToNewContentType(ShowingContentType.LIST_OF_REGIONS)
                    findRegionView.showRegions(listRegion)
                }
                finishLoadingData()
            }
        }
    }

    override fun loadListOfDonorCenters(region: Region) {
        currentLoadingDataType = LoadingDataType.LIST_OF_CENTERS
        findRegionView.moveUiToNewLoadingState(LoadingDataState.Loading)
        donorUaApi.loadCitiesForRegion(region) { listOfCenters ->
            if (currentLoadingDataType == LoadingDataType.LIST_OF_CENTERS) {
                if (listOfCenters.isEmpty()) {
                    findRegionView.moveUiToNewLoadingState(LoadingDataState.Failed)
                } else {
                    findRegionView.moveUiToNewLoadingState(LoadingDataState.Loaded)
                    findRegionView.moveUiToNewContentType(ShowingContentType.LIST_OF_CENTERS)
                    findRegionView.showDonorCenters(listOfCenters)
                }
                finishLoadingData()
            }
        }
    }

    override fun loadDonorCenter(donorCenterPreview: DonorCenterPreview) {
        currentLoadingDataType = LoadingDataType.SINGLE_CENTER
        findRegionView.moveUiToNewLoadingState(LoadingDataState.Loading)
        donorUaApi.loadListOfRegions { listRegion ->
            if (currentLoadingDataType == LoadingDataType.LIST_OF_REGIONS) {
                if (listRegion.isEmpty()) {
                    findRegionView.moveUiToNewLoadingState(LoadingDataState.Failed)
                } else {
                    findRegionView.moveUiToNewLoadingState(LoadingDataState.Loaded)
                    findRegionView.showRegions(listRegion)
                }
            }
        }
    }
}