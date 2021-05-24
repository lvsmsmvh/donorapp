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

    private lateinit var action: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        when (intent.action) {
            ACTION_SHOW_FAVORITE_CENTERS ->
                navigateToCentersFragment(centersStorage().favoriteCenters.getList())
            else ->
                navigateToRegionsFragment(centersStorage().listOfDonorCenter!!.toListOfRegions())
        }
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


    companion object {
        const val ACTION_SHOW_ALL_CENTERS = "action_show_all_centers"
        const val ACTION_SHOW_FAVORITE_CENTERS = "action_show_favorite_centers"
    }


    private fun log(string: String) {
        Log.i("tag_search", string + "")
    }
}