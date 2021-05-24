package com.medicalapp.donorua.ui.addcheck.addinfo

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.medicalapp.donorua.R
import com.medicalapp.donorua.model.check.Check
import com.medicalapp.donorua.ui.search.SearchActivity
import com.medicalapp.donorua.utils.extensions.*
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.acitvity_add_check_hint.view.*
import kotlinx.android.synthetic.main.activity_add_check.*
import kotlinx.android.synthetic.main.activity_add_check.activity_add_check_hint_container
import kotlinx.android.synthetic.main.activity_add_check.view.*

class AddCheckActivity: AppCompatActivity() {

    private lateinit var check: Check

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_check)

        check = intent.getStringExtra(KEY_CHECK)!!.toCheckClass()

        doOnStart()
    }

    private fun doOnStart() {
        updateDateAndTime()
        updateCenter()
        updateImage()
        updateHints()
    }


    // update fields methods

    @SuppressLint("SetTextI18n")
    private fun updateDateAndTime() {
        activity_add_check_text_date.text = "Дата: " + check.dateAndTime.getDateInStringFormat()
        activity_add_check_text_time.text = "Час: " + check.dateAndTime.getTimeInStringFormat()
    }

    @SuppressLint("SetTextI18n")
    private fun updateCenter() {
        activity_add_check_text_donor_center.apply {
            if (check.center != null) {
                text = "Центр: " + check.center!!.name
                show()
            } else hide()

            Log.i("tag_menu", "update center view. center is null = " + (check.center == null))

        }
    }

    private fun updateImage() {
        val uri = Uri.parse(check.uriOnImage)
        Picasso.get().load(uri).into(activity_add_check_image)
    }

    private fun updateHints() {
        activity_add_check_hint_container.removeAllViews() // cleaer hints

        if (check.center == null) addHintAddCenter() else addHintEditCenter()
        addHintRemoveCheck()
    }


    // hints

    private fun addHintAddCenter() {
        with(getHintView()) {
            activity_add_check_hint_text.text = "Додати центр: "

            val image = ContextCompat.getDrawable(this@AddCheckActivity, R.drawable.ic_baseline_add_24)
            activity_add_check_hint_button.setImageDrawable(image)

            activity_add_check_hint_button.setOnClickListener { selectNewCenter() }

            this@AddCheckActivity.activity_add_check_hint_container.addView(this)
        }
    }

    private fun addHintEditCenter() {
        with(getHintView()) {
            activity_add_check_hint_text.text = "Змінити центр: "

            val image = ContextCompat.getDrawable(this@AddCheckActivity, R.drawable.ic_baseline_edit_24)
            activity_add_check_hint_button.setImageDrawable(image)

            activity_add_check_hint_button.setOnClickListener { selectNewCenter() }

            this@AddCheckActivity.activity_add_check_hint_container.addView(this)
        }
    }

    private fun addHintRemoveCheck() {
        with(getHintView()) {
            activity_add_check_hint_text.text = "Видалити цей чек: "

            val image = ContextCompat.getDrawable(this@AddCheckActivity, R.drawable.ic_baseline_clear_24)
            activity_add_check_hint_button.setImageDrawable(image)

            activity_add_check_hint_button.setOnClickListener { deleteCheck() }

            this@AddCheckActivity.activity_add_check_hint_container.addView(this)
        }
    }

    private fun getHintView() = layoutInflater.inflate(R.layout.acitvity_add_check_hint,
            null) as LinearLayout




    // other
    private fun selectNewCenter() {
        startActivityForResult(Intent(this, SearchActivity::class.java)
                .setAction(SearchActivity.ACTION_GET_CENTER), SearchActivity.REQUEST_CODE_GET_CENTER)
    }


    private fun deleteCheck() {
        checksStorage().remove(check)
        finish()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        when (requestCode) {
            SearchActivity.REQUEST_CODE_GET_CENTER -> {
                val id = data!!.getIntExtra(SearchActivity.KEY_CENTER_ID, -1)
                check.center = centersStorage().getCenterById(id)
                Log.i("tag_menu", "center id got " + id)
                updateCenter()
                updateHints()
            }
        }
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.add_check_menu, menu)
        return true
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        Log.i("tag_menu", "on menu clicked")
        if (check.center == null) {
            // TODO show dialog
        } else {
            checksStorage().add(check)
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

    companion object {
        const val KEY_CHECK = "key_check"
    }
}