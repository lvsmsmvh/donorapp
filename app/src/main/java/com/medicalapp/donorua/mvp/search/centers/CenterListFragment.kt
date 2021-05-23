package com.medicalapp.donorua.mvp.search.centers

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.medicalapp.donorua.R
import com.medicalapp.donorua.model.center.DonorCenter
import kotlinx.android.synthetic.main.fragment_region_list.*

class CenterListFragment(
    private val listOfCenters: List<DonorCenter>,
    private val onCenterClicked: (center: DonorCenter) -> Unit
): Fragment(R.layout.fragment_centers) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initData()
    }

    private fun initData() {
        val centerListAdapter = CenterListAdapter()

        centerListAdapter.set(
            listOfCenters = listOfCenters.sortedBy { it.name }
        ) { clickedCenter ->
            onCenterClicked(clickedCenter)
        }

        with(region_list_recycler_view) {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = centerListAdapter
        }
    }
}