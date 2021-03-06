package com.medicalapp.donorua.ui.main.fragment.centers

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.medicalapp.donorua.R
import com.medicalapp.donorua.ui.search.SearchActivity
import com.medicalapp.donorua.utils.extensions.centersStorage
import com.medicalapp.donorua.utils.extensions.checksStorage
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
        fragment_centers_fb_search.setOnClickListener {
            requireActivity().startActivity(
                Intent(requireContext(), SearchActivity::class.java).apply {
                    action = SearchActivity.ACTION_SHOW_ALL_CENTERS
                }
            )
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_star_filled, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.menu.menu_star_filled -> {
                requireActivity().startActivity(
                    Intent(requireContext(), SearchActivity::class.java).apply {
                        action = SearchActivity.ACTION_SHOW_FAVORITE_CENTERS
                    }
                )
            }
        }
        return true
    }

    override fun makeToast(text: String) {
        Toast.makeText(requireContext(), text + "", Toast.LENGTH_SHORT).show()
    }

    override fun openFindCentersActivity() =
        requireActivity().simpleNavigate(SearchActivity::class.java)
}