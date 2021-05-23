package com.medicalapp.donorua.mvp.search.regions

import androidx.recyclerview.widget.RecyclerView
import com.medicalapp.donorua.model.center.Region
import com.medicalapp.donorua.utils.centers.DonorCentersStorage

interface IRegionListContract {
    interface IRegionListView {
        fun navigateToNextActivity(region: Region)
    }

    interface IRegionListPresenter {
        fun initRecyclerViewAndFillWithData(rv: RecyclerView, storage: DonorCentersStorage)
    }
}