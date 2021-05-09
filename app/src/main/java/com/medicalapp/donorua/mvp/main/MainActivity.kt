package com.medicalapp.donorua.mvp.main

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import com.medicalapp.donorua.App
import com.medicalapp.donorua.R
import com.medicalapp.donorua.mvp.main.fragment.home.HomeFragment
import com.medicalapp.donorua.mvp.main.fragment.profile.ProfileFragment
import com.medicalapp.donorua.mvp.registration.FirstOpenActivity
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
                else -> ProfileFragment()
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