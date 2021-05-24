package com.medicalapp.donorua.ui.search

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.FrameLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.medicalapp.donorua.R
import com.medicalapp.donorua.model.center.City
import com.medicalapp.donorua.model.center.DonorCenter
import com.medicalapp.donorua.model.center.Region
import com.medicalapp.donorua.ui.center.CenterActivity
import com.medicalapp.donorua.ui.search.centers.CenterListFragment
import com.medicalapp.donorua.ui.search.city.CityListFragment
import com.medicalapp.donorua.ui.search.regions.RegionListFragment
import com.medicalapp.donorua.utils.extensions.centersStorage
import com.medicalapp.donorua.utils.extensions.toListOfRegions
import kotlinx.android.synthetic.main.activity_search.*

class SearchActivity: AppCompatActivity() {

    private lateinit var fragmentContainer: FrameLayout
    private lateinit var regions: List<Region>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        fragmentContainer = activity_search_fragment_container
        regions = centersStorage().listOfDonorCenter!!.toListOfRegions()

        navigateToRegionsFragment(regions)
    }


    private fun navigateToRegionsFragment(list: List<Region>) {
        simpleNavigate(fragment = RegionListFragment(list) { regionClicked ->
            navigateToCitiesFragment(regionClicked.cities)
        })
    }

    private fun navigateToCitiesFragment(list: List<City>) {
        simpleNavigate(fragment = CityListFragment(list) { cityClicked ->
            navigateToCentersFragment(cityClicked.centers)
        })
    }

    private fun navigateToCentersFragment(list: List<DonorCenter>) {
        simpleNavigate(fragment = CenterListFragment(list) { centerClicked ->
            Toast.makeText(this, "Center : " + centerClicked.name, Toast.LENGTH_SHORT).show()
            navigateToCenterActivity(centerClicked)
        })
    }

    private fun simpleNavigate(fragment: Fragment) = supportFragmentManager.beginTransaction()
            .replace(R.id.activity_search_fragment_container, fragment)
            .addToBackStack(fragment.id.toString())
            .commit()

    private fun navigateToCenterActivity(center: DonorCenter) {
        startActivity(Intent(this, CenterActivity::class.java).apply {
            putExtra(CenterActivity.KEY_CENTER_ID, center.id)
        })
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount == 1) {
            finish()
        } else super.onBackPressed()
    }



    private fun log(string: String) {
        Log.i("tag_search", string + "")
    }
}