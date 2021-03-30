package com.medicalapp.donorua.mvp.main.fragment.profile

import com.medicalapp.donorua.App
import com.medicalapp.donorua.R
import com.medicalapp.donorua.model.user.Gender
import com.medicalapp.donorua.utils.extensions.getPersonAgeInYears
import com.medicalapp.donorua.utils.helper.SharedPrefsHelper

class ProfilePresenter(
    private val profileView: IProfileFragmentContract.IProfileFragmentView
): IProfileFragmentContract.IProfileFragmentPresenter {

    var sharedPrefsHelper: SharedPrefsHelper = App.instance.sharedPrefs

    init {
        setUserInfo()
    }

    private fun setUserInfo() {
        sharedPrefsHelper.getUser()?.let { user ->
            profileView.setUserName(user.name)

            profileView.setUserSurname(user.surname)

            profileView.setProfileIcon(when (user.gender) {
                Gender.MALE -> R.drawable.profile_icon_man
                Gender.FEMALE -> R.drawable.profile_icon_woman
                Gender.OTHER -> R.drawable.profile_icon_incognito
            })

            profileView.setUserBloodGroup(user.bloodGroup)

            profileView.setUserAge(user.birthDate.getPersonAgeInYears())
        }
    }

    override fun onEditDataClick() {
        profileView.navigateToEditActivity()
    }
}