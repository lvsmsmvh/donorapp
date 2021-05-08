package com.medicalapp.donorua.mvp.registration

import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.medicalapp.donorua.App
import com.medicalapp.donorua.R
import com.medicalapp.donorua.model.user.BloodGroup
import com.medicalapp.donorua.model.user.Gender

class FirstOpenPresenter(
    private val firstOpenView: IFirstOpenContract.IFirstOpenView
) : IFirstOpenContract.IFirstOpenPresenter {

    private val sharedPrefsHelper = App.instance.sharedPrefs

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

    override fun restoreUser() = sharedPrefsHelper.getUser()

//    private fun validateIfCanGoNext(user: User): Boolean {
//        if (user.name.isNullOrEmpty()) {
//            firstOpenView.makeToastWithText("Имя не должно быть пустым!")
//            return false
//        }
//
//        if (user.surname.isNullOrEmpty()) {
//            firstOpenView.makeToastWithText("Фамилия не должна быть пустой!")
//            return false
//        }
//
//        if (user.gender == null) {
//            firstOpenView.makeToastWithText("Пожалуйста, укажите Ваш пол!")
//            return false
//        }
//
//        if (user.bloodGroup == null) {
//            firstOpenView.makeToastWithText("Пожалуйста, укажите Вашу группу крови!")
//            return false
//        }
//        return true
//    }


    override fun onSubmitClick() {
        sharedPrefsHelper.saveUser(firstOpenView.collectData())
        firstOpenView.navigateToMainActivity()
    }
}