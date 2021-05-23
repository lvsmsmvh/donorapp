package com.medicalapp.donorua.ui.registration

import android.view.LayoutInflater
import com.google.android.material.chip.ChipGroup
import com.medicalapp.donorua.model.user.User

interface IFirstOpenContract {

    interface IFirstOpenPresenter {
        fun initBloodContainer(container: ChipGroup)
        fun initGenderContainer(container: ChipGroup)
        fun onSubmitClick()
        fun onBackPressedClick()
    }

    interface IFirstOpenView {
        fun makeToastWithText(str: String)
        fun collectData(): User
        fun getMyLayoutInflater(): LayoutInflater
        fun navigateToMainActivity()
    }
}