package com.medicalapp.donorua.mvp.findregion

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.medicalapp.donorua.R
import com.medicalapp.donorua.model.states.LoadingDataState
import com.medicalapp.donorua.utils.LogTags
import com.medicalapp.donorua.utils.donorua.DonorUaApi
import com.medicalapp.donorua.utils.donorua.model.Region
import com.medicalapp.donorua.utils.extensions.hide
import com.medicalapp.donorua.utils.extensions.show
import kotlinx.android.synthetic.main.activity_find_region.*

class FindRegionActivity: AppCompatActivity(), IFindRegionContract.IFindRegionView
{
    private lateinit var presenter: FindRegionPresenter
    private lateinit var adapter: RegionListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_find_region)

        initRecyclerViewAdapter()
        presenter = FindRegionPresenter(this)
        presenter.loadData()
    }

    private fun initRecyclerViewAdapter() {
        adapter = RegionListAdapter()
        find_region_recycler_view.layoutManager = LinearLayoutManager(this)
        find_region_recycler_view.adapter = adapter
    }

    override fun moveUiToState(loadingDataState: LoadingDataState) {
        when (loadingDataState) {
            LoadingDataState.Loading -> {
                find_region_lay_loading.show()
                find_region_lay_loaded.hide()
                find_region_lay_failed.hide()
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

    override fun showRegions(list: List<Region>) {
        adapter.set(list) {
            presenter.onRegionClick(it)
        }
    }

    override fun getApiInstance() = DonorUaApi(this)
}