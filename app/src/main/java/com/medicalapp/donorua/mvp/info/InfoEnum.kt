package com.medicalapp.donorua.mvp.info

import com.medicalapp.donorua.R

enum class InfoEnum(
    val imgResId: Int,
    val titleResId: Int,
    val descriptionResId: Int,
) {
    Coffee(R.drawable.ic_coffee, R.string.info_coffee_for_donor, R.string.info_coffee_for_donor_details),
    Morning(R.drawable.ic_sun, R.string.info_donor_morning, R.string.info_donor_morning_details),
    DoNotDo(R.drawable.ic_alert, R.string.info_dont_do_it, R.string.info_dont_do_it_details),
    EntireBlood(R.drawable.ic_cell, R.string.info_entire_blood, R.string.info_entire_blood_details),
    HowToBecome(R.drawable.ic_question_mark, R.string.info_how_to_become_a_donor, R.string.info_how_to_become_a_donor_details),
    HowToRecover(R.drawable.ic_document, R.string.info_how_to_recover, R.string.info_how_to_recover_details),
    Lose(R.drawable.ic_angry, R.string.info_lose_consciousness, R.string.info_lose_consciousness_details),
    Plasma(R.drawable.ic_cell2, R.string.info_plasma, R.string.info_plasma_details),
    TimeStore(R.drawable.ic_time, R.string.info_time_to_store_blood, R.string.info_time_to_store_blood_details),
    Trombocity(R.drawable.ic_tear_blood, R.string.info_trombocity, R.string.info_trombocity_details),
    Eat(R.drawable.ic_food, R.string.info_what_to_eat, R.string.info_what_to_eat_details)
}