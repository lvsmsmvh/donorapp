package com.medicalapp.donorua.ui.search.regions

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.medicalapp.donorua.R
import com.medicalapp.donorua.model.center.Region
import com.medicalapp.donorua.utils.extensions.sortRegionsByAlphabet
import kotlinx.android.synthetic.main.fragment_region_list.*

class RegionListFragment(
    private val listOfRegions: List<Region>,
    private val onRegionClicked: (region: Region) -> Unit
): Fragment(R.layout.fragment_region_list) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initData()
    }

    private fun initData() {
        val regionListAdapter = RegionListAdapter()

        regionListAdapter.set(
            listOfRegions = listOfRegions.sortRegionsByAlphabet()
        ) { clickedRegion ->
            onRegionClicked(clickedRegion)
        }

        with(city_list_recycler_view) {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = regionListAdapter
        }
    }
}