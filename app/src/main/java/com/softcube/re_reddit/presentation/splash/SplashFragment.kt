package com.softcube.re_reddit.presentation.splash

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.softcube.re_reddit.application.base.BaseFragment
import com.softcube.re_reddit.common.autoCleared
import com.softcube.re_reddit.databinding.FragmentSplashBinding

/**
 * com.softcube.re_reddit.presentation.splash
 *
 * Created by Wilson Garcia on 6/5/21
 * Copyright © 2021 Wilson Garcia. All rights reserved.
 */
class SplashFragment : BaseFragment() {

	private var binding by autoCleared<FragmentSplashBinding>()

	override fun onCreateView(
		inflater: LayoutInflater,
		container: ViewGroup?,
		savedInstanceState: Bundle?
	): View {
		binding = FragmentSplashBinding.inflate(inflater, container, false)
		binding.lifecycleOwner = this

		return binding.root
	}

}
