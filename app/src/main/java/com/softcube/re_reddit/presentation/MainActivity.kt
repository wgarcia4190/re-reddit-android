package com.softcube.re_reddit.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import com.softcube.re_reddit.R
import com.softcube.re_reddit.databinding.ActivityMainBinding

/**
 * com.softcube.re_reddit.presentation
 *
 * Created by Wilson Garcia on 6/5/21
 * Copyright © 2021 Wilson Garcia. All rights reserved.
 */
class MainActivity: AppCompatActivity() {
	private lateinit var binding: ActivityMainBinding
	private lateinit var navController: NavController

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		binding = ActivityMainBinding.inflate(layoutInflater)
		setContentView(binding.root)

		navController = Navigation.findNavController(this, R.id.main_fragment)
	}

	override fun onSupportNavigateUp(): Boolean = navController.navigateUp()

	override fun onRestoreInstanceState(savedInstanceState: Bundle) {
		super.onRestoreInstanceState(savedInstanceState)
		onCreate(savedInstanceState)
	}
}
