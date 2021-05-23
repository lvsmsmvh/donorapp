package com.medicalapp.donorua.mvp.search.regions

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.medicalapp.donorua.R
import com.medicalapp.donorua.model.center.Region
import com.medicalapp.donorua.utils.extensions.centersStorage
import kotlinx.android.synthetic.main.activity_region_list.*

class RegionListActivity: AppCompatActivity(),
        IRegionListContract.IRegionListView
{

    private lateinit var presenter: RegionListPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_region_list)

        presenter = RegionListPresenter(this)

        initData()
    }

    private fun initData() {
        presenter.initRecyclerViewAndFillWithData(
            rv = region_list_recycler_view,
            storage = centersStorage()
        )
    }

    override fun navigateToNextActivity(region: Region) {
        Log.i("tag_storage", "Navigate to next activity with region " + region.name)
    }
}