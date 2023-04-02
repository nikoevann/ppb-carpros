package com.e.porsche.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.e.porsche.R
import com.e.porsche.managers.UserManager
import com.e.porsche.ui.accessories.AccessoriesFragment
import com.e.porsche.ui.salon.SalonFragment
import com.e.porsche.ui.account.AccountFragment
import com.e.porsche.ui.constructor.CarConstActivity
import com.e.porsche.ui.logining.CreateAccountActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val RESULT_CREATE_ACCOUNT = 256
    private var selected_tab_id: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        UserManager.getUpUser()


        val navController = findNavController(R.id.nav_host_fragment)
        setupActionBarWithNavController(navController, AppBarConfiguration(
            setOf(
                R.id.nav_salon,
                R.id.nav_accessories,
                R.id.nav_account
            )
        ))
        nav_view.setupWithNavController(navController)

        nav_view.setOnNavigationItemSelectedListener { menuItem ->
            when(menuItem.itemId) {
                R.id.nav_salon -> {
                    selected_tab_id = R.id.nav_salon
                    replaceFragment(SalonFragment())
                }
                R.id.nav_accessories -> {
                    selected_tab_id = R.id.nav_accessories
                    replaceFragment(AccessoriesFragment())
                }
                R.id.nav_account -> {
                    accountPressed()
                }
            }
            true
        }

        selected_tab_id = R.id.nav_salon
        replaceFragment(SalonFragment())
    }

    private fun accountPressed() {
        if(!UserManager.isUserLogged) {
            startActivityForResult(Intent(this, CreateAccountActivity::class.java), RESULT_CREATE_ACCOUNT)
            this.overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left)

        } else {
            replaceFragment(AccountFragment())
        }
    }


    fun logoutPressed() {
        replaceFragment(SalonFragment())
        selected_tab_id = R.id.nav_salon
        nav_view.selectedItemId = selected_tab_id
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when(requestCode) {
            RESULT_CREATE_ACCOUNT -> {
                if(resultCode == Activity.RESULT_OK) {

                    if(!UserManager.isUserLogged) {
                        nav_view.selectedItemId = selected_tab_id
                    } else {
                        replaceFragment(AccountFragment())
                    }

                }
            }
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.nav_host_fragment, fragment)
        transaction.commit()
    }
}
