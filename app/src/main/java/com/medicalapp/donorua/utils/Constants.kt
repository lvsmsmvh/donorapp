package com.medicalapp.donorua.utils

object SharedPrefsConst {
    const val SHARED_PREFS_NAME = "shared_prefs"
    const val SHARED_PREFS_USER_DATA = "user_data"
    const val SHARED_PREFS_DONOR_CENTER = "donor_centers"
    const val SHARED_PREFS_FAV_CENTERS = "donor_fav_centers"
    const val SHARED_PREFS_CHECKS = "checks"
}

object LogTags {
    const val TAG_API = "tag_api"
    const val TAG_PHOTO = "tag_photo"
}

object Api {
    const val URL_DOMAIN = "https://donor.ua/"
    const val PAGE_HOME = "centers/regions"
    const val MAX_TIMEOUT_LOADING = 180 * 1000 // 3min
}

object Parameters {
    const val isAcceptingAllChecks = false
}

const val ukrainianAlphabet = "0123456789" +
        "АаБбВвГгҐґДдЕе" +
        "ЄєЖжЗзИиІіЇїЙй" +
        "КкЛлМмНнОоПпРр" +
        "СсТтУуФфХхЦцЧч" +
        "ШшЩщЬьЮюЯя"