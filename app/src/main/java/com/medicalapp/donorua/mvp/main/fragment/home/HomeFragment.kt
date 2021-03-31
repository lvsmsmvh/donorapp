package com.medicalapp.donorua.mvp.main.fragment.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.medicalapp.donorua.R
import com.medicalapp.donorua.mvp.findregion.FindRegionActivity
import com.medicalapp.donorua.utils.extensions.simpleNavigate
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
        fragment_home_button_find_donor.setOnClickListener {
            presenter.onFindDonorClick()
        }
    }

    override fun navigateToFindCityActivity() =
        requireActivity().simpleNavigate(FindRegionActivity::class.java)
}