package com.medicalapp.donorua.utils.helper

import android.content.Context
import com.google.gson.Gson
import com.medicalapp.donorua.model.user.User
import com.medicalapp.donorua.utils.SharedPrefsConst.SHARED_PREFS_DONOR_CENTER
import com.medicalapp.donorua.utils.SharedPrefsConst.SHARED_PREFS_NAME
import com.medicalapp.donorua.utils.SharedPrefsConst.SHARED_PREFS_USER_DATA
import com.medicalapp.donorua.model.center.DonorCenter
import com.medicalapp.donorua.model.check.Check
import com.medicalapp.donorua.utils.SharedPrefsConst.SHARED_PREFS_CHECKS
import com.medicalapp.donorua.utils.SharedPrefsConst.SHARED_PREFS_FAV_CENTERS


class SharedPrefsHelper(context: Context) {

    private val gson = Gson()

    private val sharedPreferences = context.getSharedPreferences(
        SHARED_PREFS_NAME,
        Context.MODE_PRIVATE
    )

    // fav centers

    fun saveListOfChecks(list: List<Check>) {
        val str = parseListOfChecksToStr(list)
        sharedPreferences.edit().putString(SHARED_PREFS_CHECKS, str).apply()
    }

    fun getListOfChecks(): List<Check> {
        sharedPreferences.getString(SHARED_PREFS_CHECKS, null)?.let { str ->
            return parseListOfChecksFromStr(str)
        }
        return emptyList()
    }


    // fav centers

    fun saveListOfFavoriteCenters(list: List<DonorCenter>) {
        val str = parseListOfCentersToStr(list)
        sharedPreferences.edit().putString(SHARED_PREFS_FAV_CENTERS, str).apply()
    }

    fun getListOfFavoriteCenters(): List<DonorCenter> {
        sharedPreferences.getString(SHARED_PREFS_FAV_CENTERS, null)?.let { str ->
            return parseListOfCentersFromStr(str)
        }
        return emptyList()
    }


    // all list of centers

    fun saveListOfCenters(list: List<DonorCenter>) {
        val str = parseListOfCentersToStr(list)
        sharedPreferences.edit().putString(SHARED_PREFS_DONOR_CENTER, str).apply()
    }

    fun getListOfCenters(): List<DonorCenter> {
        sharedPreferences.getString(SHARED_PREFS_DONOR_CENTER, null)?.let { str ->
            return parseListOfCentersFromStr(str)
        }
        return emptyList()
    }


    // user

    fun saveUser(user: User) {
        sharedPreferences.edit().putString(SHARED_PREFS_USER_DATA, parseUserToStr(user)).apply()
    }

    fun getUser(): User {
        sharedPreferences.getString(SHARED_PREFS_USER_DATA, null)?.let { str ->
            return parseUserFromStr(str)
        }
        return User()
    }

    data class AllChecks(val listOfChecks: List<Check>) // we use this cuz we cant parse a list to json
    private fun parseListOfChecksToStr(list: List<Check>): String =
        gson.toJson(AllChecks(list))
    private fun parseListOfChecksFromStr(str: String): List<Check> =
        gson.fromJson(str, AllChecks::class.java).listOfChecks

    data class AllDonorCenters(val listOfCenters: List<DonorCenter>)
    private fun parseListOfCentersToStr(list: List<DonorCenter>): String =
        gson.toJson(AllDonorCenters(list))
    private fun parseListOfCentersFromStr(str: String): List<DonorCenter> =
        gson.fromJson(str, AllDonorCenters::class.java).listOfCenters

    private fun parseUserToStr(user: User): String =
        gson.toJson(user)
    private fun parseUserFromStr(str: String): User =
        gson.fromJson(str, User::class.java)

}