package com.softcube.re_reddit.application.di

import com.softcube.re_reddit.presentation.splash.SplashViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

/**
 * com.softcube.re_reddit.application.di
 *
 * Created by Wilson Garcia on 6/5/21
 * Copyright © 2021 Wilson Garcia. All rights reserved.
 */
val viewModelModule = module {
	viewModel {
		SplashViewModel(
			authUseCase = get(named("authenticateUseCase"))
		)
	}
}
