package com.medicalapp.donorua.utils.extensions

import com.medicalapp.donorua.App
import com.medicalapp.donorua.model.user.User

fun User.isNotRegistered() =
    name.isNullOrBlank() || surname.isNullOrBlank() || gender == null || bloodGroup == null

fun User.isRegistered() = !isNotRegistered()

var currentUser: User
    get() = App.instance.sharedPrefs.getUser()
    set(value) = App.instance.sharedPrefs.saveUser(value)