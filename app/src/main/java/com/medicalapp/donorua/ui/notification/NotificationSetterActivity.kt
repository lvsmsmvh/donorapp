package com.medicalapp.donorua.ui.notification

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.RadioButton
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.datepicker.CalendarConstraints
import com.google.android.material.datepicker.DateValidatorPointForward
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import com.medicalapp.donorua.R
import com.medicalapp.donorua.model.notification.NotificationVisit
import com.medicalapp.donorua.model.notification.PeriodBeforeSending
import com.medicalapp.donorua.utils.extensions.getDateInStringFormat
import com.medicalapp.donorua.utils.extensions.toCalendar
import com.medicalapp.donorua.ui.notification.utils.NotificationHelper
import kotlinx.android.synthetic.main.activity_notification_setter.*
import java.util.*

class NotificationSetterActivity: AppCompatActivity() {

    private lateinit var menu: Menu

    private var visitTime: Calendar? = null
    private var hour: Int? = null
    private var minute: Int? = null
    private var periodBeforeSending: PeriodBeforeSending? = null
    private var withSound: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notification_setter)

        initData()
        showDatePicker()
    }

    private fun initData() {
        // init radio buttons
        PeriodBeforeSending.values().forEachIndexed { index, periodBeforeSending ->
            val radioButton = RadioButton(this)
            radioButton.text = periodBeforeSending.text
            activity_notification_setter_radio_button_container.addView(radioButton)
            if (index == 2) {
                radioButton.isChecked = true
            }
        }
     }


    private fun showDatePicker() {
        // Makes only dates from today forward selectable.
        val constraintsBuilder = CalendarConstraints.Builder()
                .setValidator(DateValidatorPointForward.now())

        val datePicker = MaterialDatePicker.Builder.datePicker()
                .setTitleText("?????????????? ????????")
                .setSelection(MaterialDatePicker.todayInUtcMilliseconds())
                .setCalendarConstraints(constraintsBuilder.build())
                .build()

        datePicker.addOnPositiveButtonClickListener {
            datePicker.selection?.let {
                visitTime = it.toCalendar()
                showTimePicker()
                return@addOnPositiveButtonClickListener
            }
            showDatePicker()
        }

        datePicker.addOnDismissListener {
            if (visitTime == null)
                finish()
        }

        datePicker.show(supportFragmentManager, "tagDatePicker")
    }

    private fun showTimePicker() {
        val timePicker =
            MaterialTimePicker.Builder()
                .setTimeFormat(TimeFormat.CLOCK_24H)
                .setHour(12)
                .setMinute(10)
                .setTitleText("?????????????? ??????")
                .build()

        timePicker.addOnPositiveButtonClickListener {
            hour = timePicker.hour
            minute = timePicker.minute
            showSaveButton()
        }
        timePicker.addOnDismissListener {
            if (hour == null || minute == null)
                showDatePicker()
        }

        timePicker.show(supportFragmentManager, "tagTimePicker")
    }

    private fun hideSaveButton() {
        menu.findItem(R.id.menu_confirm_button_agree).isVisible = false
    }

    private fun showSaveButton() {
        menu.findItem(R.id.menu_confirm_button_agree).isVisible = true
    }

    private fun collectDataFromUiElements() {
        // periodBeforeSending

        val radioId = activity_notification_setter_radio_button_container
            .checkedRadioButtonId

        val radioButton = activity_notification_setter_radio_button_container
            .findViewById<RadioButton>(radioId)

        periodBeforeSending = PeriodBeforeSending.values().first { it.text == radioButton.text }

        // sound on notification

        withSound = activity_notification_setter_switch_button_sound.isChecked
    }

    private fun tryToSave() {
        collectDataFromUiElements()

        MaterialAlertDialogBuilder(this)
            .setTitle("?????????????????????? ?????????????????????????")
            .setMessage(getConfirmationText())
            .setPositiveButton(
                R.string.OK
            ) { dialog, _ ->
                save()
            }
            .setNegativeButton(
                R.string.cancel
            ) { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }

    private fun getConfirmationText() =
        "????????: " + visitTime!!.getDateInStringFormat() +
                "\n??????: $hour:$minute" +
                "\n\n???? ????????????: " + (if (withSound) "??????" else "????") +
                "\n\n???????????????? ????: " + periodBeforeSending!!.text

    private fun save() {
        visitTime!!.set(Calendar.HOUR_OF_DAY, hour!!)
        visitTime!!.set(Calendar.MINUTE, minute!!)

        val notificationVisit = NotificationVisit(
            visitTime = visitTime!!,
            periodBeforeSending = periodBeforeSending!!,
            withSound = withSound
        )

        NotificationHelper.getInstance(this).setNotificationVisit(notificationVisit)

//        NotificationHelper.getInstance(this).pushNotificationVisit(notificationVisit)

        finish()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_confirm, menu)
        this.menu = menu!!
        hideSaveButton()
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        tryToSave()
        return true
    }
}