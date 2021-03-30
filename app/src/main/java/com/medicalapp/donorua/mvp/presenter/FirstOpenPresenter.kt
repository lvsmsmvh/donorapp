package com.medicalapp.donorua.mvp.presenter

import com.google.android.material.chip.Chip
import com.medicalapp.donorua.App
import com.medicalapp.donorua.mvp.contract.IFirstOpenContract
import com.medicalapp.donorua.mvp.model.firstopen.FirstOpenCollectModel
import com.medicalapp.donorua.mvp.model.user.BloodGroup
import com.medicalapp.donorua.mvp.model.user.Gender
import com.medicalapp.donorua.mvp.model.user.User
import com.medicalapp.donorua.utils.extensions.isOlderThanEighteen
import com.medicalapp.donorua.utils.extensions.setTwentyYearsAgo
import java.util.*

class FirstOpenPresenter(
    private val firstOpenView: IFirstOpenContract.IFirstOpenView
) : IFirstOpenContract.IFirstOpenPresenter {

    private val sharedPrefsHelper = App.instance.sharedPrefs

    init {
        createBloodChips()
        createGenderChips()
        initDatePicker()
    }


    private fun createBloodChips() {
        val list = mutableListOf<Chip>()
        BloodGroup.values().forEach { bloodGroup ->
            val newBloodChip = firstOpenView.createChip().apply {
                text = bloodGroup.nameId
            }
            list.add(newBloodChip)
        }
        firstOpenView.fillBloodTypeContainer(list)
    }

    private fun createGenderChips() {
        val list = mutableListOf<Chip>()
        Gender.values().forEach { gender ->
            val newGender = firstOpenView.createChip().apply {
                text = gender.nameId
            }
            list.add(newGender)
        }
        firstOpenView.fillGenderTypeContainer(list)
    }

    private fun initDatePicker() {
        firstOpenView.setNewDatePickerDate(Calendar.getInstance().setTwentyYearsAgo())
    }


    private fun validateIfCanGoNext(collectedData: FirstOpenCollectModel): Boolean {
        if (collectedData.name.isNullOrEmpty()) {
            firstOpenView.makeToastWithText("Имя не должно быть пустым!")
            return false
        }

        if (collectedData.surname.isNullOrEmpty()) {
            firstOpenView.makeToastWithText("Фамилия не должна быть пустой!")
            return false
        }

        if (collectedData.gender == null) {
            firstOpenView.makeToastWithText("Пожалуйста, укажите Ваш пол!")
            return false
        }

        if (collectedData.bloodGroup == null) {
            firstOpenView.makeToastWithText("Пожалуйста, укажите Вашу группу крови!")
            return false
        }

        if (!collectedData.birthDate.isOlderThanEighteen()) {
            firstOpenView.makeToastWithText("Вам должно быть больше 18 лет!")
            return false
        }

        return true
    }

    private fun generateNewUser(collectedData: FirstOpenCollectModel)
        = User(
            name = collectedData.name!!,
            surname = collectedData.surname!!,
            gender = collectedData.gender!!,
            bloodGroup = collectedData.bloodGroup!!,
            birthDate = collectedData.birthDate
        )

    override fun onSubmitClick() {
        firstOpenView.collectData().apply {
            if (validateIfCanGoNext(this)) {
                sharedPrefsHelper.saveUser(generateNewUser(this))
                firstOpenView.navigateToMainActivity()
            }
        }
    }
}