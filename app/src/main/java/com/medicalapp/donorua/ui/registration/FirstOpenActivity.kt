package com.medicalapp.donorua.ui.registration

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.medicalapp.donorua.R
import com.medicalapp.donorua.ui.main.MainActivity
import com.medicalapp.donorua.model.user.BloodGroup
import com.medicalapp.donorua.model.user.Gender
import com.medicalapp.donorua.model.user.User
import com.medicalapp.donorua.utils.extensions.checkedChipText
import com.medicalapp.donorua.utils.extensions.currentUser
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

        initData()
    }

    private fun initData() {
        first_open_date_picker.maxDate = Calendar.getInstance().timeInMillis

        presenter.initBloodContainer(first_open_blood_types_container)
        presenter.initGenderContainer(first_open_gender_types_container)

        fillFieldsWithCurrentUserData()
    }

    private fun fillFieldsWithCurrentUserData() {
        with (currentUser) {
            name?.let { first_open_et_name.setText(it) }
            surname?.let { first_open_et_surname.setText(it) }

            gender?.let {
                first_open_gender_types_container.findChipWithTheTextAndMarkIt(it.nameId)
            }

            bloodGroup?.let {
                first_open_blood_types_container.findChipWithTheTextAndMarkIt(it.nameId)
            }

            birthDate.let { calendar ->
                first_open_date_picker.init(
                    calendar.get(Calendar.YEAR),
                    calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.DAY_OF_MONTH),
                    null
                )
            }
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

        first_open_gender_types_container.checkedChipText()?.let { chipText ->
            user.gender = Gender.values().first { it.nameId == chipText }
        }

        first_open_blood_types_container.checkedChipText()?.let { chipText ->
            user.bloodGroup = BloodGroup.values().first { it.nameId == chipText }
        }

        user.birthDate = Calendar.getInstance().apply {
            set(Calendar.DAY_OF_MONTH, first_open_date_picker.dayOfMonth)
            set(Calendar.MONTH, first_open_date_picker.month)
            set(Calendar.YEAR, first_open_date_picker.year)
        }

        return user
    }

    override fun onBackPressed() {
        presenter.onBackPressedClick()
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_confirm, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        presenter.onSubmitClick()
        return true
    }


    override fun makeToastWithText(str: String) {
        MaterialAlertDialogBuilder(this)
            .setTitle("Помилка")
            .setMessage(str)
            .setPositiveButton(
                R.string.OK
            ) { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }

    override fun getMyLayoutInflater() = layoutInflater

    override fun navigateToMainActivity() =
        simpleNavigateAndFinishAfter(MainActivity::class.java)
}