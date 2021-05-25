package com.medicalapp.donorua.ui.check.addinfo

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.medicalapp.donorua.R
import com.medicalapp.donorua.model.check.Check
import com.medicalapp.donorua.ui.check.fullscreen.FullScreenImageActivity
import com.medicalapp.donorua.ui.main.fragment.checks.CheckViewHolder
import com.medicalapp.donorua.ui.search.SearchActivity
import com.medicalapp.donorua.utils.extensions.*
import kotlinx.android.synthetic.main.acitvity_add_check_hint.view.*
import kotlinx.android.synthetic.main.activity_add_check.*
import kotlinx.android.synthetic.main.activity_add_check.view.*
import kotlinx.android.synthetic.main.item_check.*


class EditCheckActivity: AppCompatActivity() {

    private lateinit var check: Check

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_check)

        check = intent.getStringExtra(KEY_CHECK)!!.toCheckClass()

        doOnStart()
    }

    private fun doOnStart() {
        updateCardView()
        updateHints()
    }

    private fun updateCardView() {
        CheckViewHolder(item_check_card_view).bind(
            check = check
        ) {
            openFullScreenCheckImage()
        }
    }

    private fun openFullScreenCheckImage() {
        val intent = Intent(this, FullScreenImageActivity::class.java)
            .setData(Uri.parse(check.uriOnImage))

        startActivity(intent)
    }


    private fun updateHints() {
        activity_add_check_hint_container.removeAllViews() // clear hints

        if (check.center == null)
            addHintAddCenter()
        else
            addHintEditCenter()

        if (checksStorage().isInTheList(check))
            addHintRemoveCheck()
    }


    // hints

    private fun addHintAddCenter() {
        addHint(
            name = "Додати центр: ",
            drawableRes = R.drawable.ic_baseline_add_24,
            onClickListener = { selectNewCenter() }
        )
    }

    private fun addHintEditCenter() {
        addHint(
            name = "Змінити центр: ",
            drawableRes = R.drawable.ic_baseline_edit_24,
            onClickListener = { selectNewCenter() }
        )
    }

    private fun addHintRemoveCheck() {
        addHint(
            name = "Видалити цей чек: ",
            drawableRes = R.drawable.ic_baseline_clear_24,
            onClickListener = { deleteCheckClick() }
        )
    }

    private fun addHint(name: String, drawableRes: Int, onClickListener: View.OnClickListener) {
        val ll = layoutInflater.inflate(R.layout.acitvity_add_check_hint, null) as LinearLayout
        with(ll) {
            activity_add_check_hint_text.text = name

            val image = ContextCompat.getDrawable(this@EditCheckActivity, drawableRes)
            activity_add_check_hint_button.setImageDrawable(image)

            activity_add_check_hint_button.setOnClickListener(onClickListener)

            this@EditCheckActivity.activity_add_check_hint_container.addView(this)
        }
    }

    // other
    private fun selectNewCenter() {
        startActivityForResult(Intent(this, SearchActivity::class.java)
                .setAction(SearchActivity.ACTION_GET_CENTER), SearchActivity.REQUEST_CODE_GET_CENTER)
    }


    private fun deleteCheckClick() {
        MaterialAlertDialogBuilder(this)
            .setTitle("Видалити чек?")
            .setPositiveButton(
                R.string.OK
            ) { dialog, _ ->
                checksStorage().remove(check)
                finish()
            }
            .setNegativeButton(
                R.string.cancel
            ) { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        Log.i("tag_menu", "result code = " + resultCode + ", request code = " + requestCode)
        if (resultCode == RESULT_CANCELED) return

        when (requestCode) {
            SearchActivity.REQUEST_CODE_GET_CENTER -> {
                val id = data!!.getIntExtra(SearchActivity.KEY_CENTER_ID, -1)
                check.center = centersStorage().getCenterById(id)

                updateCardView()
                updateHints()
            }
        }
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_confirm, menu)
        return true
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (check.center == null) {
            MaterialAlertDialogBuilder(this)
                .setTitle("Дані заповнено не повністю")
                .setMessage("Додайте центр, в якому ви отримали чек, щоб зберегти інформацію.")
                .setPositiveButton(R.string.OK
                ) { dialog, _ ->
                    dialog.dismiss()
                }
                .show()
            return true
        }

        if (checksStorage().isInTheList(check)) {
            MaterialAlertDialogBuilder(this)
                .setTitle("Помилка")
                .setMessage("Такий чек вже існує у вашому списку.")
                .setPositiveButton(R.string.OK
                ) { dialog, _ ->
                    dialog.dismiss()
                }
                .show()
            return true
        }

        checksStorage().add(check)
        finish()

        return true
    }

    companion object {
        const val KEY_CHECK = "key_check"
    }
}