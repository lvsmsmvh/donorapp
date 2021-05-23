package com.medicalapp.donorua.mvp.main.fragment.centers

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.medicalapp.donorua.R
import com.medicalapp.donorua.mvp.search.regions.RegionListActivity
import com.medicalapp.donorua.utils.extensions.centersStorage
import com.medicalapp.donorua.utils.extensions.simpleNavigate
import kotlinx.android.synthetic.main.fragment_centers.*

class CentersFragment : Fragment(R.layout.fragment_centers),
    ICentersFragmentContract.ICentersView
{

    private lateinit var presenter: ICentersFragmentContract.ICentersPresenter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initControl()

        presenter = CentersPresenter(
            view = this,
            storage = requireContext().centersStorage()
                .apply { restoreCenters() }
        )
    }

    private fun initControl() {
        fragment_centers_btn_find_centers.setOnClickListener {
            presenter.onFindCentersClick()
        }
    }

    override fun makeToast(text: String) {
        Toast.makeText(requireContext(), text + "", Toast.LENGTH_SHORT).show()
    }

    override fun openFindCentersActivity() =
        requireActivity().simpleNavigate(RegionListActivity::class.java)
}