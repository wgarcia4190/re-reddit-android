package com.softcube.re_reddit.presentation.splash

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.softcube.re_reddit.application.base.BaseFragment
import com.softcube.re_reddit.common.SessionManager
import com.softcube.re_reddit.common.Status
import com.softcube.re_reddit.common.UNKNOWN_ERROR_MESSAGE
import com.softcube.re_reddit.common.autoCleared
import com.softcube.re_reddit.databinding.FragmentSplashBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * com.softcube.re_reddit.presentation.splash
 *
 * Created by Wilson Garcia on 6/5/21
 * Copyright © 2021 Wilson Garcia. All rights reserved.
 */
class SplashFragment : BaseFragment() {

	private var binding by autoCleared<FragmentSplashBinding>()
	private val viewModel: SplashViewModel by viewModel()

	override fun onCreateView(
		inflater: LayoutInflater,
		container: ViewGroup?,
		savedInstanceState: Bundle?
	): View {
		binding = FragmentSplashBinding.inflate(inflater, container, false)
		binding.lifecycleOwner = this

		observeViewState()
		viewModel.authenticate()

		return binding.root
	}

	private fun observeViewState() {
		viewModel.stateLiveData.observe(
			viewLifecycleOwner,
			Observer { state ->
				when (state.status) {
					Status.SUCCESS -> {
						state.data?.let {
							SessionManager.accessToken = it
							Log.d("SplashFragment", it.token)

							navigateTo(SplashFragmentDirections.toPostList())
						}

					}
					Status.ERROR -> {
						state.error?.let { Log.e("SplashFragment", it.message ?: UNKNOWN_ERROR_MESSAGE) }
					}
					else -> print("NONE")
				}
			}
		)
	}

}
