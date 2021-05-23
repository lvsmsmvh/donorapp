package com.medicalapp.donorua.mvp.search.regions

import android.content.Context
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.medicalapp.donorua.utils.centers.DonorCentersStorage
import com.medicalapp.donorua.utils.extensions.toListOfRegions

class RegionListPresenter(
    private val view: IRegionListContract.IRegionListView,
): IRegionListContract.IRegionListPresenter {

    private lateinit var context: Context

    override fun initRecyclerViewAndFillWithData(rv: RecyclerView, storage: DonorCentersStorage) {
        context = rv.context

        rv.layoutManager = LinearLayoutManager(rv.context)

        val adapter = RegionListAdapter()

        adapter.set(
            listOfRegions = storage.listOfDonorCenter!!.toListOfRegions()
        ) { clickedRegion ->
            view.navigateToNextActivity(clickedRegion)
        }

        rv.adapter = adapter
    }
}