package com.medicalapp.donorua.utils

object SharedPrefsConst {
    const val SHARED_PREFS_NAME = "shared_prefs"
    const val SHARED_PREFS_USER_DATA = "user_data"
    const val SHARED_PREFS_DONOR_CENTER = "donor_centers"
}

object LogTags {
    const val TAG_CHIP = "tag_chip"
    const val TAG_CALENDAR = "tag_calendar"
    const val TAG_API = "tag_api"
    const val TAG_PHOTO = "tag_photo"
    const val TAG_RECYCLER = "tag_recycler"
    const val TAG_USER = "donorua_user"
}

object Api {
    const val URL_DOMAIN = "https://donor.ua/"
    const val PAGE_HOME = "centers/regions"
    const val MAX_TIMEOUT_LOADING = 180 * 1000 // 3min
}