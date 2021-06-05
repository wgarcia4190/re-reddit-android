package com.softcube.re_reddit.application.di

import com.softcube.re_reddit.domain.repository.AuthRepository
import com.softcube.re_reddit.domain.usecase.AuthenticateBaseUseCase
import com.softcube.re_reddit.domain.usecase.AuthenticateUseCase
import org.koin.core.qualifier.named
import org.koin.dsl.module

/**
 * com.softcube.re_reddit.application.di
 *
 * Created by Wilson Garcia on 6/5/21
 * Copyright © 2021 Wilson Garcia. All rights reserved.
 */
val useCasesModule = module {
	single(named("authenticateUseCase")) { AuthenticateUseCase(get()) }
}

fun provideAuthenticateUseCase(repository: AuthRepository): AuthenticateBaseUseCase {
	return AuthenticateUseCase(repository)
}

