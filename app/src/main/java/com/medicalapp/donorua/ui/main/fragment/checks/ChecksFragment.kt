package com.medicalapp.donorua.ui.main.fragment.checks

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.medicalapp.donorua.R
import com.medicalapp.donorua.ui.addcheck.capture.CaptureReceiptActivity
import com.medicalapp.donorua.utils.extensions.simpleNavigate
import kotlinx.android.synthetic.main.fragment_checks.*

class ChecksFragment: Fragment(R.layout.fragment_checks) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initControl()
        initData()
    }


    private fun initControl() {
        fragment_checks_button_add.setOnClickListener {
            requireActivity().simpleNavigate(CaptureReceiptActivity::class.java)
        }
    }

    private fun initData() {

    }
}