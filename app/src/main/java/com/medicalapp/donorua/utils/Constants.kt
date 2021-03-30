package com.medicalapp.donorua.utils

object SharedPrefsConst {
    const val SHARED_PREFS_NAME = "shared_prefs"
    const val SHARED_PREFS_USER_DATA = "user_data"
    const val SHARED_PREFS_USER_SUB = "user_sub"
    const val SHARED_PREFS_IS_FIRST_TIME = "is_first_time"
    const val SHARED_PREFS_HOROSCOPE_RATING = "horoscope_rating"
    const val SHARED_PREFS_HOROSCOPE_FULL = "horoscope_full"
    const val SHARED_PREFS_SUBS_TRIAL_DAYS = "subs_trial_days"
    const val SHARED_PREFS_SUBS_PRICE = "subs_price"
}

object LogTags {
    const val TAG_CHIP = "tag_chip"
    const val TAG_CALENDAR = "tag_calendar"
    const val TAG_PLACES = "tag_places"
    const val TAG_API = "tag_api"
}

object Api {
    const val URL_DOMAIN = "https://donor.ua/"
    const val PAGE_HOME = "centers/regions"
    const val MAX_TIMEOUT_LOADING = 10 * 1000 // 10s
}