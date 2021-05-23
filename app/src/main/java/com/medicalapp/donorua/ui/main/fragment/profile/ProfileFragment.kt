package com.medicalapp.donorua.ui.main.fragment.profile

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.medicalapp.donorua.R
import com.medicalapp.donorua.model.user.BloodGroup
import com.medicalapp.donorua.ui.registration.FirstOpenActivity
import com.medicalapp.donorua.utils.extensions.drawable
import com.medicalapp.donorua.utils.extensions.simpleNavigateAndFinishAfter
import kotlinx.android.synthetic.main.fragment_profile.*

class ProfileFragment : Fragment(R.layout.fragment_profile),
    IProfileFragmentContract.IProfileFragmentView
{

    private lateinit var presenter: IProfileFragmentContract.IProfileFragmentPresenter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initControl()

        presenter = ProfilePresenter(this)
    }

    private fun initControl() {
        fragment_profile_button_edit.setOnClickListener {
            presenter.onEditDataClick()
        }
    }

    override fun setProfileIcon(resourceId: Int) {
        fragment_profile_picture.setImageDrawable(
            activity!!.drawable(resourceId)
        )
    }

    override fun setUserName(str: String) {
        fragment_profile_name.text = str
    }

    override fun setUserSurname(str: String) {
        fragment_profile_surname.text = str
    }

    override fun setUserAge(age: Int) {
        fragment_profile_age.text = age.toString()
    }

    override fun setUserBloodGroup(bloodGroup: BloodGroup) {
        fragment_profile_blood_group.text = bloodGroup.nameId
    }

    override fun navigateToEditActivity() =
        activity!!.simpleNavigateAndFinishAfter(FirstOpenActivity::class.java)
}