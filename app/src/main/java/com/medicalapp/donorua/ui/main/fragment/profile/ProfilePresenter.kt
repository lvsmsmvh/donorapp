package com.medicalapp.donorua.ui.main.fragment.profile

import com.medicalapp.donorua.App
import com.medicalapp.donorua.R
import com.medicalapp.donorua.model.user.Gender
import com.medicalapp.donorua.utils.extensions.currentUser
import com.medicalapp.donorua.utils.extensions.getPersonAgeInYears
import com.medicalapp.donorua.utils.extensions.isNotRegistered
import com.medicalapp.donorua.utils.helper.SharedPrefsHelper
import java.lang.Exception

class ProfilePresenter(
    private val profileView: IProfileFragmentContract.IProfileFragmentView
): IProfileFragmentContract.IProfileFragmentPresenter {

    init {
        setUserInfo()
    }

    private fun setUserInfo() {
        with (currentUser) {
            if (isNotRegistered()) {
                throw Exception("User was not registered, but HomeFragment has been created.")
            }
            profileView.setUserName(name!!)
            profileView.setUserSurname(surname!!)
            profileView.setProfileIcon(when (gender!!) {
                Gender.MALE -> R.drawable.profile_icon_man
                Gender.FEMALE -> R.drawable.profile_icon_woman
                Gender.OTHER -> R.drawable.profile_icon_incognito
            })
            profileView.setUserBloodGroup(bloodGroup!!)
            profileView.setUserAge(birthDate.getPersonAgeInYears())
        }
    }

    override fun onEditDataClick() {
        profileView.navigateToEditActivity()
    }
}