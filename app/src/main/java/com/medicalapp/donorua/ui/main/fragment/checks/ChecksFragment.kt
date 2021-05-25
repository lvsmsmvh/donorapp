package com.medicalapp.donorua.ui.main.fragment.checks

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.medicalapp.donorua.R
import com.medicalapp.donorua.model.check.Check
import com.medicalapp.donorua.ui.check.addinfo.EditCheckActivity
import com.medicalapp.donorua.ui.check.capture.CaptureReceiptActivity
import com.medicalapp.donorua.ui.notification.NotificationSetterActivity
import com.medicalapp.donorua.utils.extensions.checksStorage
import com.medicalapp.donorua.utils.extensions.hide
import com.medicalapp.donorua.utils.extensions.simpleNavigate
import com.medicalapp.donorua.utils.extensions.toJson
import kotlinx.android.synthetic.main.fragment_checks.*

class ChecksFragment: Fragment(R.layout.fragment_checks) {

    private lateinit var listOfChecks: List<Check>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initControl()
        initData()

        if (listOfChecks.isEmpty())
            fragment_checks_rv.hide()
        else
            initRecycler()
    }


    private fun initControl() {
        fragment_checks_button_add.setOnClickListener {
            requireActivity().simpleNavigate(CaptureReceiptActivity::class.java)
        }
        fragment_checks_button_set_notification.setOnClickListener {
            requireActivity().simpleNavigate(NotificationSetterActivity::class.java)
        }
    }

    private fun initData() {
        listOfChecks = requireContext().checksStorage().getList()
    }

    private fun initRecycler() {
        val checkListAdapter = CheckListAdapter()

        checkListAdapter.set(
            listOfChecks = listOfChecks
        ) { clickedCheck ->
            openActivityCheckDetails(check = clickedCheck)
        }

        with(fragment_checks_rv) {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = checkListAdapter
            setHasFixedSize(true)
        }
    }

    override fun onResume() {
        super.onResume()
        val isListActual = requireContext().checksStorage().getList() == listOfChecks

        if (!isListActual) {
            initData()
            initRecycler()
        }
    }


    private fun openActivityCheckDetails(check: Check) {
        val intent = Intent(requireContext(), EditCheckActivity::class.java)
            .putExtra(EditCheckActivity.KEY_CHECK, check.toJson())

        requireActivity().startActivity(intent)
    }
}