package com.medicalapp.donorua.mvp.findregion

import android.util.Log
import com.medicalapp.donorua.model.states.LoadingDataState
import com.medicalapp.donorua.utils.LogTags
import com.medicalapp.donorua.utils.donorua.model.Region

class FindRegionPresenter(
    private val findRegionView: IFindRegionContract.IFindRegionView
    ) : IFindRegionContract.IFindRegionPresenter {

    private val donorUaApi = findRegionView.getApiInstance()

    override fun loadData() {
        findRegionView.moveUiToState(LoadingDataState.Loading)
        donorUaApi.loadListOfRegions { listRegion ->
            if (listRegion.isEmpty()) {
                findRegionView.moveUiToState(LoadingDataState.Failed)
            } else {
                findRegionView.moveUiToState(LoadingDataState.Loaded)
                findRegionView.showRegions(listRegion)
            }
        }
    }

    override fun onRegionClick(region: Region) {
        Log.i(LogTags.TAG_API, "on region click " + region.name)
    }
}