package com.medicalapp.donorua.mvp.main.fragment.home

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import com.medicalapp.donorua.R
import com.medicalapp.donorua.mvp.capturereceipt.CaptureReceiptActivity
import com.medicalapp.donorua.mvp.findregion.FindRegionActivity
import com.medicalapp.donorua.mvp.info.InfoActivity
import com.medicalapp.donorua.utils.centers.DonorCentersStorage
import com.medicalapp.donorua.utils.extensions.centersStorage
import com.medicalapp.donorua.utils.extensions.simpleNavigate
import com.medicalapp.donorua.utils.extensions.toListOfRegions
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment(R.layout.fragment_home),
        IHomeFragmentContract.IHomeFragmentView
{

    private lateinit var presenter: IHomeFragmentContract.IHomeFragmentPresenter


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initControl()

        presenter = HomePresenter(this)
    }

    private fun initControl() {
        val storage = requireContext().centersStorage()
        storage.listOfDonorCenter = null


        fragment_home_button_find_donor.setOnClickListener {
            DonorCentersStorage.logStorage("home onClick")
            storage.restoreCenters()

            storage.listOfDonorCenter?.let { list ->
                log("home: start converting (to regions)")

                val regions = list.toListOfRegions()

                log("home: converting finished")
            }
        }
        fragment_home_button_info_for_donor.setOnClickListener {
            presenter.onInfoForDonorClick()
        }
        fragment_home_button_new_check.setOnClickListener {
            presenter.onSaveACheckClick()
        }
    }

    override fun navigateToFindCityActivity() =
        requireActivity().simpleNavigate(FindRegionActivity::class.java)

    override fun navigateToCaptureReceiptActivity() =
        requireActivity().simpleNavigate(CaptureReceiptActivity::class.java)

    override fun navigateToInfoActivity() =
        requireActivity().simpleNavigate(InfoActivity::class.java)


    fun log(string: String) {
        Log.i("tag_storage", string + "")
    }

    fun logRegion(region: com.medicalapp.donorua.model.center.Region) {
        log("Region : name = " + region.name + " , cities = " + region.cities.size)
    }
}