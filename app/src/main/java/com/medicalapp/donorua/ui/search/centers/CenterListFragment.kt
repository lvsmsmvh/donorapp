package com.medicalapp.donorua.ui.search.centers

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.medicalapp.donorua.R
import com.medicalapp.donorua.model.center.DonorCenter
import com.medicalapp.donorua.ui.search.SearchActivity
import com.medicalapp.donorua.utils.extensions.centersStorage
import com.medicalapp.donorua.utils.extensions.hide
import com.medicalapp.donorua.utils.extensions.show
import com.medicalapp.donorua.utils.extensions.sortCentersByAlphabet
import kotlinx.android.synthetic.main.fragment_center_list.*

class CenterListFragment(
    private val listOfCenters: List<DonorCenter>,
    private val onCenterClicked: (center: DonorCenter) -> Unit
): Fragment(R.layout.fragment_center_list) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initData(listOfCenters)
    }

    private fun initData(list: List<DonorCenter>) {
        if (list.isEmpty()) {
            center_list_recycler_view.hide()
            return
        }

        center_list_recycler_view.show()

        val centerListAdapter = CenterListAdapter()

        centerListAdapter.set(
            listOfCenters = list.sortCentersByAlphabet()
        ) { clickedCenter ->
            onCenterClicked(clickedCenter)
        }

        with(center_list_recycler_view) {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = centerListAdapter
        }
    }


    override fun onResume() {
        super.onResume()
        val activity = requireActivity() as SearchActivity

        if (activity.intent.action == SearchActivity.ACTION_SHOW_FAVORITE_CENTERS) {
            initData(requireContext().centersStorage().favoriteCenters.getList())
        }
    }
}