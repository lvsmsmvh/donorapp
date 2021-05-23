package com.medicalapp.donorua.ui.search.centers

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.medicalapp.donorua.R
import com.medicalapp.donorua.model.center.DonorCenter
import com.medicalapp.donorua.utils.extensions.sortCentersByAlphabet
import kotlinx.android.synthetic.main.fragment_center_list.*

class CenterListFragment(
    private val listOfCenters: List<DonorCenter>,
    private val onCenterClicked: (center: DonorCenter) -> Unit
): Fragment(R.layout.fragment_center_list) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initData()
    }

    private fun initData() {
        val centerListAdapter = CenterListAdapter()

        centerListAdapter.set(
            listOfCenters = listOfCenters.sortCentersByAlphabet()
        ) { clickedCenter ->
            onCenterClicked(clickedCenter)
        }

        with(center_list_recycler_view) {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = centerListAdapter
        }
    }
}