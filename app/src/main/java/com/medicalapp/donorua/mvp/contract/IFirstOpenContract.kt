package com.medicalapp.donorua.mvp.contract

import com.google.android.material.chip.Chip
import com.medicalapp.donorua.mvp.model.firstopen.FirstOpenCollectModel
import java.util.*

interface IFirstOpenContract {
    interface IFirstOpenPresenter {
        fun onSubmitClick()
    }

    interface IFirstOpenView {
        fun makeToastWithText(str: String)
        fun collectData(): FirstOpenCollectModel
        fun setNewDatePickerDate(calendar: Calendar)
        fun navigateToMainActivity()
        fun createChip() : Chip
        fun fillBloodTypeContainer(list: List<Chip>)
        fun fillGenderTypeContainer(list: List<Chip>)
    }
}