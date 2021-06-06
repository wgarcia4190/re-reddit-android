package com.softcube.re_reddit.presentation.post

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
import com.softcube.re_reddit.common.extension.toggleLoading
import com.softcube.re_reddit.databinding.FragmentPostListBinding
import com.softcube.re_reddit.presentation.splash.SplashFragmentDirections
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * com.softcube.re_reddit.presentation.post
 *
 * Created by Wilson Garcia on 6/6/21
 * Copyright © 2021 Wilson Garcia. All rights reserved.
 */
class PostListFragment: BaseFragment() {

	private var binding by autoCleared<FragmentPostListBinding>()
	private val viewModel: PostListViewModel by viewModel()

	override fun onCreateView(
		inflater: LayoutInflater,
		container: ViewGroup?,
		savedInstanceState: Bundle?
	): View {
		binding = FragmentPostListBinding.inflate(inflater, container, false)
		binding.lifecycleOwner = this

		observeViewState()
		viewModel.getPosts()

		setupEvents()

		return binding.root
	}

	private fun setupEvents() {
		binding.pullToRefresh.setOnRefreshListener {
			viewModel.refresh()
		}
	}

	private fun observeViewState() {
		viewModel.stateLiveData.observe(
			viewLifecycleOwner,
			Observer { state ->
				when (state.status) {
					Status.LOADING -> binding.pullToRefresh.toggleLoading(true)
					Status.SUCCESS -> {
						binding.pullToRefresh.toggleLoading(false)
						state.data?.let {
							Log.d("PostListFragment", it.size.toString())
						}

					}
					Status.ERROR -> {
						binding.pullToRefresh.toggleLoading(false)
						state.error?.let { Log.e("PostListFragment", it.message ?: UNKNOWN_ERROR_MESSAGE) }
					}
					else -> print("NONE")
				}
			}
		)
	}
}
