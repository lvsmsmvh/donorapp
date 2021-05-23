package com.medicalapp.donorua.ui.main

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.medicalapp.donorua.R
import com.medicalapp.donorua.ui.main.fragment.centers.CentersFragment
import com.medicalapp.donorua.ui.main.fragment.home.HomeFragment
import com.medicalapp.donorua.ui.main.fragment.profile.ProfileFragment
import com.medicalapp.donorua.ui.registration.FirstOpenActivity
import com.medicalapp.donorua.utils.extensions.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initBottomNavListener()
        setFirstOpenedPage()
        configureViewForNotRegistered()
    }

    private fun initBottomNavListener() {
        bottom_navigation.setOnNavigationItemSelectedListener {
            simpleNavigate(when (it.itemId) {
                R.id.nav_home -> HomeFragment()
                R.id.nav_centers -> CentersFragment()
                R.id.nav_checks -> HomeFragment() // TODO CheckFragment()
                R.id.nav_profile -> ProfileFragment()
                else -> HomeFragment()
            })
            return@setOnNavigationItemSelectedListener true
        }
    }

    private fun setFirstOpenedPage() {
        simpleNavigate(HomeFragment())
    }

    @SuppressLint("ShowToast")
    private fun configureViewForNotRegistered() {
        if (currentUser.isRegistered()) {
            return
        }

        // remove Home button if not registered
        bottom_navigation.menu.removeItem(R.id.nav_profile)

        // show banner
        with(banner) {
            setLeftButtonAction { dismiss() }
            setRightButtonAction { simpleNavigateAndFinishAfter(FirstOpenActivity::class.java) }
            visibility = View.VISIBLE
        }
    }


    private fun simpleNavigate(toFragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(
            R.id.fragment_container,
            toFragment
        ).commit()
    }
}