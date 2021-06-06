package com.softcube.re_reddit.application.base

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.softcube.re_reddit.common.CoroutineTestRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Rule
import org.koin.test.AutoCloseKoinTest

/**
 * com.softcube.re_reddit.application.base
 *
 * Created by Wilson Garcia on 6/5/21
 * Copyright © 2021 Wilson Garcia. All rights reserved.
 */
abstract class BaseViewModelTest : AutoCloseKoinTest() {
	@get:Rule
	open val instantExecutorRule = InstantTaskExecutorRule()

	@ExperimentalCoroutinesApi
	@get:Rule
	open val coroutineTestRule = CoroutineTestRule()

	abstract fun prepareViewModel()
}
