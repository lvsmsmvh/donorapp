package com.medicalapp.donorua.ui.registration

import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.medicalapp.donorua.R
import com.medicalapp.donorua.model.user.BloodGroup
import com.medicalapp.donorua.model.user.Gender
import com.medicalapp.donorua.model.user.User
import com.medicalapp.donorua.utils.extensions.currentUser

class FirstOpenPresenter(
    private val firstOpenView: IFirstOpenContract.IFirstOpenView
) : IFirstOpenContract.IFirstOpenPresenter {

    override fun initBloodContainer(container: ChipGroup) {
        fillContainerWithChips(container, BloodGroup.values().map { it.nameId })
    }

    override fun initGenderContainer(container: ChipGroup) {
        fillContainerWithChips(container, Gender.values().map { it.nameId })
    }

    private fun fillContainerWithChips(container: ChipGroup, names: List<String>) {
        names.forEach { nameForChip ->
            val newChip = firstOpenView.getMyLayoutInflater()
                .inflate(R.layout.chip_item_for_blood_type, container, false) as Chip
            newChip.text = nameForChip
            container.addView(newChip)
        }
    }

    private fun validateIfCanGoNext(user: User): Boolean {
        if (user.name.isNullOrEmpty()) {
            firstOpenView.makeToastWithText("Ім'я не повинно бути пустим!")
            return false
        }

        if (user.surname.isNullOrEmpty()) {
            firstOpenView.makeToastWithText("Прізвище не повинно бути пустим!")
            return false
        }

        if (user.gender == null) {
            firstOpenView.makeToastWithText("Будь ласка, вкажіть Вашу стать!")
            return false
        }

        if (user.bloodGroup == null) {
            firstOpenView.makeToastWithText("Будь ласка, вкажіть Вашу групу крові!")
            return false
        }
        return true
    }

    override fun onBackPressedClick() {
        firstOpenView.navigateToMainActivity()
    }

    override fun onSubmitClick() {
        val user = firstOpenView.collectData()

        if (validateIfCanGoNext(user)) {
            currentUser = user
            firstOpenView.navigateToMainActivity()
        }
    }
}