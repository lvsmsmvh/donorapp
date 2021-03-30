package com.medicalapp.donorua.mvp.contract

import com.medicalapp.donorua.mvp.model.user.BloodGroup

interface IProfileFragmentContract {
    interface IProfileFragmentPresenter {
        fun onEditDataClick()
    }

    interface IProfileFragmentView {
        fun navigateToEditActivity()
        fun setProfileIcon(resourceId: Int)
        fun setUserName(str: String)
        fun setUserSurname(str: String)
        fun setUserAge(age: Int)
        fun setUserBloodGroup(bloodGroup: BloodGroup)
    }
}