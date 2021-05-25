package com.medicalapp.donorua.ui.main

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.medicalapp.donorua.R
import com.medicalapp.donorua.ui.info.InfoActivity
import com.medicalapp.donorua.ui.main.fragment.centers.CentersFragment
import com.medicalapp.donorua.ui.main.fragment.checks.ChecksFragment
import com.medicalapp.donorua.ui.main.fragment.profile.ProfileFragment
import com.medicalapp.donorua.ui.notification.NotificationSetterActivity
import com.medicalapp.donorua.ui.registration.FirstOpenActivity
import com.medicalapp.donorua.ui.search.SearchActivity
import com.medicalapp.donorua.utils.extensions.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initBottomNavListener()
        setFirstOpenedPage()
        configureViewForNotRegistered()
        initNavigationDrawer()
    }

    private fun initBottomNavListener() {
        bottom_navigation.setOnNavigationItemSelectedListener {
            simpleNavigate(when (it.itemId) {
                R.id.nav_centers -> CentersFragment()
                R.id.nav_checks -> ChecksFragment()
                R.id.nav_profile -> ProfileFragment()
                else -> CentersFragment()
            })
            return@setOnNavigationItemSelectedListener true
        }
    }

    private fun setFirstOpenedPage() {
        simpleNavigate(CentersFragment())
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

    private fun initNavigationDrawer() {
        topAppBar.setNavigationOnClickListener {
            drawerLayout.openDrawer(activity_main_navigation_drawer)
        }

        activity_main_navigation_drawer.setNavigationItemSelectedListener { menuItem ->
            drawerLayout.closeDrawer(activity_main_navigation_drawer)

            when (menuItem.itemId) {
                R.id.drawer_menu_item_find_center -> {
                    goSearchActivityWithAction(SearchActivity.ACTION_SHOW_ALL_CENTERS)
                }
                R.id.drawer_menu_item_my_centers -> {
                    goSearchActivityWithAction(SearchActivity.ACTION_SHOW_FAVORITE_CENTERS)
                }
                R.id.drawer_menu_item_info_for_donor -> {
                    simpleNavigate(InfoActivity::class.java)
                }
                R.id.drawer_menu_item_notification -> {
                    simpleNavigate(NotificationSetterActivity::class.java)
                }
                else -> { }
            }
            return@setNavigationItemSelectedListener true
        }
    }

    private fun goSearchActivityWithAction(action: String) {
        val intent = Intent(this, SearchActivity::class.java)
            .setAction(action)

        startActivity(intent)
    }

    private fun simpleNavigate(toFragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(
            R.id.fragment_container,
            toFragment
        ).commit()
    }
}