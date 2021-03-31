package com.medicalapp.donorua.mvp.findregion

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.medicalapp.donorua.R
import com.medicalapp.donorua.model.findcenterstates.LoadingDataState
import com.medicalapp.donorua.model.findcenterstates.ShowingContentType
import com.medicalapp.donorua.mvp.findregion.city.CityListAdapter
import com.medicalapp.donorua.mvp.findregion.regions.RegionListAdapter
import com.medicalapp.donorua.utils.donorua.DonorUaApi
import com.medicalapp.donorua.utils.donorua.model.City
import com.medicalapp.donorua.utils.donorua.model.Region
import com.medicalapp.donorua.utils.extensions.hide
import com.medicalapp.donorua.utils.extensions.show
import kotlinx.android.synthetic.main.activity_find_region.*

class FindRegionActivity: AppCompatActivity(), IFindRegionContract.IFindRegionView
{
    private lateinit var presenter: FindRegionPresenter
    private var currentShowingContentType = ShowingContentType.LIST_OF_REGIONS

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_find_region)

        configureRecyclers()

        presenter = FindRegionPresenter(this)
        presenter.loadListOfRegions()
    }

    private fun configureRecyclers() {
        listOf(
            find_region_recycler_view_region,
            find_region_recycler_view_center
        ).forEach {
            it.layoutManager = LinearLayoutManager(this)
        }
    }


    override fun moveUiToNewLoadingState(loadingDataState: LoadingDataState) {
        when (loadingDataState) {
            LoadingDataState.Loading -> {
                find_region_lay_loading.show()
                find_region_lay_loaded.hide()
                find_region_lay_failed.hide()

                find_region_lay_regions.hide()
                find_region_lay_centers.hide()
                find_region_lay_single_center.hide()
            }
            LoadingDataState.Loaded -> {
                find_region_lay_loading.hide()
                find_region_lay_loaded.show()
                find_region_lay_failed.hide()
            }
            LoadingDataState.Failed -> {
                find_region_lay_loading.hide()
                find_region_lay_loaded.hide()
                find_region_lay_failed.show()
            }
        }
    }

    override fun moveUiToNewContentType(showingContentType: ShowingContentType) {
        currentShowingContentType = showingContentType
        when (showingContentType) {
            ShowingContentType.LIST_OF_REGIONS -> {
                find_region_lay_regions.show()
                find_region_lay_centers.hide()
                find_region_lay_single_center.hide()
            }
            ShowingContentType.LIST_OF_CENTERS -> {
                find_region_lay_regions.hide()
                find_region_lay_centers.show()
                find_region_lay_single_center.hide()
            }
            ShowingContentType.SINGLE_CENTER -> {
                find_region_lay_regions.hide()
                find_region_lay_centers.hide()
                find_region_lay_single_center.show()
            }
        }
    }

    override fun getCurrentShowingContentType() = currentShowingContentType

    override fun showDonorCenters(list: List<City>) {
        val adapter = CityListAdapter()
        adapter.set(list) {
            presenter.onDonorCenterPreviewClick(it)
        }
        find_region_recycler_view_center.adapter = adapter
    }


    override fun showRegions(list: List<Region>) {
        val adapter = RegionListAdapter()
        adapter.set(list) {
            presenter.onRegionClick(it)
        }
        find_region_recycler_view_region.adapter = adapter
    }

    override fun onBackPressed() = presenter.onBackPressedClick()

    override fun getApiInstance() = DonorUaApi(this)

    override fun exit() = finish()
}