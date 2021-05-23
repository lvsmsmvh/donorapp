package com.medicalapp.donorua.ui.main.fragment.centers

import com.medicalapp.donorua.utils.helper.DonorCentersStorage

class CentersPresenter(
    private val view: ICentersFragmentContract.ICentersView,
    private val storage: DonorCentersStorage
): ICentersFragmentContract.ICentersPresenter {

    override fun onFindCentersClick() {
        if (storage.listOfDonorCenter.isNullOrEmpty()) {
            storage.restoreCenters()
            view.makeToast("Центри ще загружуються. Спробуйте пізніше.")
        } else view.openFindCentersActivity()
    }
}