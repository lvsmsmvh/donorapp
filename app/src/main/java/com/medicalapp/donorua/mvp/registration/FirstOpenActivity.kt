package com.medicalapp.donorua.mvp.registration

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.chip.Chip
import com.medicalapp.donorua.R
import com.medicalapp.donorua.mvp.main.MainActivity
import com.medicalapp.donorua.model.firstopen.FirstOpenCollectModel
import com.medicalapp.donorua.model.user.BloodGroup
import com.medicalapp.donorua.model.user.Gender
import com.medicalapp.donorua.utils.extensions.simpleNavigateAndFinishAfter
import kotlinx.android.synthetic.main.first_open_activity.*
import java.util.*

class FirstOpenActivity: AppCompatActivity(), IFirstOpenContract.IFirstOpenView {

    private lateinit var presenter: IFirstOpenContract.IFirstOpenPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.first_open_activity)

        initControl()

        presenter = FirstOpenPresenter(this)
    }

    private fun initControl() {
        first_open_submit_button.setOnClickListener {
            presenter.onSubmitClick()
        }
    }


    override fun setNewDatePickerDate(calendar: Calendar) {
        first_open_date_picker.maxDate = Calendar.getInstance().timeInMillis
        first_open_date_picker.init(
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH),
            null
        )
    }

    override fun createChip() = layoutInflater.inflate(
        R.layout.chip_item_for_blood_type,
        first_open_blood_types_container,
        false
    ) as Chip

    override fun fillBloodTypeContainer(list: List<Chip>) {
        list.forEach {
            first_open_blood_types_container.addView(it)
        }
    }

    override fun fillGenderTypeContainer(list: List<Chip>) {
        list.forEach {
            first_open_gender_types_container.addView(it)
        }
    }

    override fun makeToastWithText(str: String) {
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show()
    }


    override fun collectData() : FirstOpenCollectModel {
        val name = first_open_et_name.text.let {
            if (it.isNullOrBlank()) null else it.toString()
        }

        val surname = first_open_et_surname.text.let {
            if (it.isNullOrBlank()) null else it.toString()
        }

        val gender = first_open_gender_types_container.checkedChipId.let { chipId ->
            if (chipId == View.NO_ID)
                null
            else
                Gender.values().first { gender ->
                    gender.nameId == first_open_gender_types_container.findViewById<Chip>(chipId).text
                }
        }

        val bloodGroup = first_open_blood_types_container.checkedChipId.let { chipId ->
            if (chipId == View.NO_ID)
                null
            else
                BloodGroup.values().first { bloodGroup ->
                    bloodGroup.nameId == first_open_blood_types_container.findViewById<Chip>(chipId).text
                }
        }

        val birthDate = Calendar.getInstance().apply {
            set(Calendar.DAY_OF_MONTH, first_open_date_picker.dayOfMonth)
            set(Calendar.MONTH, first_open_date_picker.month)
            set(Calendar.YEAR, first_open_date_picker.year)
        }


        return FirstOpenCollectModel(
            name = name,
            surname = surname,
            gender = gender,
            bloodGroup = bloodGroup,
            birthDate = birthDate
        )
    }

    override fun navigateToMainActivity() =
        simpleNavigateAndFinishAfter(MainActivity::class.java)
}