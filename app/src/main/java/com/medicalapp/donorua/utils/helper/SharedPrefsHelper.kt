package com.medicalapp.donorua.utils.helper

import android.content.Context
import com.google.gson.Gson
import com.medicalapp.donorua.model.user.User
import com.medicalapp.donorua.utils.SharedPrefsConst.SHARED_PREFS_NAME
import com.medicalapp.donorua.utils.SharedPrefsConst.SHARED_PREFS_USER_DATA


class SharedPrefsHelper(context: Context) {

    private val gson = Gson()

    private val sharedPreferences = context.getSharedPreferences(
        SHARED_PREFS_NAME,
        Context.MODE_PRIVATE
    )


    fun saveUser(user: User) {
        sharedPreferences.edit().putString(SHARED_PREFS_USER_DATA, parseUserToStr(user)).apply()
    }

    fun getUser(): User {
        sharedPreferences.getString(SHARED_PREFS_USER_DATA, null)?.let { str ->
            return parseUserFromStr(str)
        }
        return User()
    }

    private fun parseUserFromStr(str: String): User = gson.fromJson(str, User::class.java)
    private fun parseUserToStr(user: User): String = gson.toJson(user)
}