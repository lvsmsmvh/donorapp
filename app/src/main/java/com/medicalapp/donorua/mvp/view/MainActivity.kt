package com.medicalapp.donorua.mvp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.medicalapp.donorua.App
import com.medicalapp.donorua.R
import com.medicalapp.donorua.mvp.view.fragment.HomeFragment
import com.medicalapp.donorua.mvp.view.fragment.ProfileFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initFragment()
    }

    private fun initFragment() {
        // init listener
        bottom_navigation.setOnNavigationItemSelectedListener {
            val selectedFragment = when (it.itemId) {
                R.id.nav_home -> HomeFragment()
                else -> ProfileFragment()
            }
            simpleNavigate(selectedFragment)
            return@setOnNavigationItemSelectedListener true
        }

        // set first open screen fragment
        simpleNavigate(HomeFragment())
    }


    private fun simpleNavigate(toFragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(
            R.id.fragment_container,
            toFragment
        ).commit()
    }
}