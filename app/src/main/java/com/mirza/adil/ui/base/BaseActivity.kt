package com.mirza.adil.ui.base

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.mirza.adil.R
import com.mirza.adil.databinding.ActivityBaseBinding
import dagger.hilt.android.AndroidEntryPoint

/*
* The class BaseActivity
*
* @author  Mirza Adil
* @email mirza.adil@gmail.com
* @version 1.0
* @since 14 Jul 2021
*
* This class is used to handle all the generic operations related to the activity, moreover as we are using the single activity architecture, we started the navigation
* graph from this BaseActivity.
*/
@AndroidEntryPoint
class BaseActivity : AppCompatActivity() {
    private lateinit var navController: NavController
    internal lateinit var binding: ActivityBaseBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
         binding = ActivityBaseBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
        NavigationUI.setupActionBarWithNavController(this, navController)

    }

    override fun onSupportNavigateUp(): Boolean {
        navController.navigateUp()
        return super.onSupportNavigateUp()
    }

}