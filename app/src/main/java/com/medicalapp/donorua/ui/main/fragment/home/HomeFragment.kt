package com.medicalapp.donorua.ui.main.fragment.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.medicalapp.donorua.R
import com.medicalapp.donorua.ui.info.InfoActivity
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
        fragment_home_button_info_for_donor.setOnClickListener {
            presenter.onInfoForDonorClick()
        }
    }

    override fun navigateToInfoActivity() =
        requireActivity().simpleNavigate(InfoActivity::class.java)
}