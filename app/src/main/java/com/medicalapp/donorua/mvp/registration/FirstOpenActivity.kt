package com.medicalapp.donorua.mvp.registration

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.medicalapp.donorua.R
import com.medicalapp.donorua.mvp.main.MainActivity
import com.medicalapp.donorua.model.user.BloodGroup
import com.medicalapp.donorua.model.user.Gender
import com.medicalapp.donorua.model.user.User
import com.medicalapp.donorua.utils.extensions.checkedChipText
import com.medicalapp.donorua.utils.extensions.findChipWithTheTextAndMarkIt
import com.medicalapp.donorua.utils.extensions.simpleNavigateAndFinishAfter
import kotlinx.android.synthetic.main.first_open_activity.*
import java.util.*

class FirstOpenActivity: AppCompatActivity(), IFirstOpenContract.IFirstOpenView {

    private lateinit var presenter: IFirstOpenContract.IFirstOpenPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.first_open_activity)

        presenter = FirstOpenPresenter(this)

        initControl()
        initData()
    }

    private fun initControl() {
        first_open_submit_button.setOnClickListener {
            presenter.onSubmitClick()
        }
    }

    private fun initData() {
        first_open_date_picker.maxDate = Calendar.getInstance().timeInMillis

        presenter.initBloodContainer(first_open_blood_types_container)
        presenter.initGenderContainer(first_open_gender_types_container)

        applyRestoredUser(presenter.restoreUser())
    }

    private fun applyRestoredUser(user: User) {
        user.name?.let { first_open_et_name.setText(it) }
        user.surname?.let { first_open_et_surname.setText(it) }

        user.gender?.let {
            first_open_gender_types_container.findChipWithTheTextAndMarkIt(it.nameId)
        }

        user.bloodGroup?.let {
            first_open_blood_types_container.findChipWithTheTextAndMarkIt(it.nameId)
        }

        user.birthDate.let { calendar ->
            first_open_date_picker.init(
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH),
                null
            )
        }
    }




    override fun collectData() : User {
        val user = User()

        user.name = first_open_et_name.text.let {
            if (it.isNullOrBlank()) null else it.toString()
        }

        user.surname = first_open_et_surname.text.let {
            if (it.isNullOrBlank()) null else it.toString()
        }

        first_open_gender_types_container.checkedChipText()?.let {
            user.gender = Gender.valueOf(it)
        }

        first_open_blood_types_container.checkedChipText()?.let {
            user.bloodGroup = BloodGroup.valueOf(it)
        }

        user.birthDate = Calendar.getInstance().apply {
            set(Calendar.DAY_OF_MONTH, first_open_date_picker.dayOfMonth)
            set(Calendar.MONTH, first_open_date_picker.month)
            set(Calendar.YEAR, first_open_date_picker.year)
        }

        return user
    }

    override fun makeToastWithText(str: String) =
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show()

    override fun getMyLayoutInflater() = layoutInflater

    override fun navigateToMainActivity() =
        simpleNavigateAndFinishAfter(MainActivity::class.java)
}