package com.medicalapp.donorua.mvp.search.city

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.medicalapp.donorua.R
import com.medicalapp.donorua.model.center.City
import kotlinx.android.synthetic.main.fragment_region_list.*

class CityListFragment(
    private val listOfCities: List<City>,
    private val onCityClicked: (city: City) -> Unit
): Fragment(R.layout.fragment_city_list) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initData()
    }

    private fun initData() {
        val cityListAdapter = CityListAdapter()

        cityListAdapter.set(
            listOfCities = listOfCities.sortedBy { it.name }
        ) { clickedCity ->
            onCityClicked(clickedCity)
        }

        with(region_list_recycler_view) {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = cityListAdapter
        }
    }
}