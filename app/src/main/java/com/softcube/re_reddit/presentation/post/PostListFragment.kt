package com.softcube.re_reddit.presentation.post

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.softcube.re_reddit.application.base.BaseFragment
import com.softcube.re_reddit.common.autoCleared
import com.softcube.re_reddit.databinding.FragmentPostListBinding
import com.softcube.re_reddit.databinding.FragmentSplashBinding

/**
 * com.softcube.re_reddit.presentation.post
 *
 * Created by Wilson Garcia on 6/6/21
 * Copyright © 2021 Wilson Garcia. All rights reserved.
 */
class PostListFragment: BaseFragment() {

	private var binding by autoCleared<FragmentPostListBinding>()

	override fun onCreateView(
		inflater: LayoutInflater,
		container: ViewGroup?,
		savedInstanceState: Bundle?
	): View {
		binding = FragmentPostListBinding.inflate(inflater, container, false)
		binding.lifecycleOwner = this


		return binding.root
	}
}
