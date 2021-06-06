package com.softcube.re_reddit.viewmodel

import com.google.common.truth.Truth
import com.softcube.re_reddit.application.base.BaseViewModelTest
import com.softcube.re_reddit.application.base.NetworkResult
import com.softcube.re_reddit.common.observeOnce
import com.softcube.re_reddit.domain.usecase.AuthenticateUseCaseTest
import com.softcube.re_reddit.presentation.splash.SplashViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

/**
 * com.softcube.re_reddit.viewmodel
 *
 * Created by Wilson Garcia on 6/5/21
 * Copyright © 2021 Wilson Garcia. All rights reserved.
 */

@RunWith(JUnit4::class)
@ExperimentalCoroutinesApi
class SplashViewModelTest: BaseViewModelTest() {

	private lateinit var viewModel: SplashViewModel
	private lateinit var useCase: AuthenticateUseCaseTest

	@Before
	fun setup(){
		prepareViewModel()
	}

	@Test
	fun `when get authentication token executed return success state`() {
		coroutineTestRule.dispatcher.runBlockingTest {

			useCase.result = NetworkResult.SUCCESS
			viewModel.authenticate()

			advanceTimeBy(600)

			viewModel.stateLiveData.observeOnce { state ->
				val token = state.data

				Truth.assertThat(state.error).isNull()
				Truth.assertThat(token).isNotNull()
				Truth.assertThat(token?.token).isNotNull()
				Truth.assertThat(token?.token).isNotEmpty()
				Truth.assertThat(token?.token).isEqualTo("--4Kz6jnKFOpzUXgHKhQS5_E3UsUfeA")
				Truth.assertThat(token?.expiresIn).isNotNull()
				Truth.assertThat(token?.expiresIn).isEqualTo(3600)
			}
		}
	}

	@Test
	fun `when get authentication token executed return failed state`() {
		coroutineTestRule.dispatcher.runBlockingTest {

			useCase.result = NetworkResult.ERROR
			viewModel.authenticate()

			advanceTimeBy(600)

			viewModel.stateLiveData.observeOnce { state ->
				Truth.assertThat(state.error).isNotNull()
				Truth.assertThat(state.data).isNull()
			}
		}
	}

	override fun prepareViewModel() {
		useCase = AuthenticateUseCaseTest()
		viewModel = SplashViewModel(useCase)
	}
}
