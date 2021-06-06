package com.softcube.re_reddit.application.di

import com.softcube.re_reddit.data.repository.AuthRepositoryImpl
import com.softcube.re_reddit.data.repository.PostRepositoryImpl
import com.softcube.re_reddit.domain.repository.AuthRepository
import com.softcube.re_reddit.domain.repository.PostRepository
import org.koin.core.qualifier.named
import org.koin.dsl.module

/**
 * com.softcube.re_reddit.application.di
 *
 * Created by Wilson Garcia on 6/5/21
 * Copyright © 2021 Wilson Garcia. All rights reserved.
 */
val repositoryModule = module {
	single<AuthRepository> { AuthRepositoryImpl(service = get(named("authService"))) }
	single<PostRepository> { PostRepositoryImpl(service = get(named("postService"))) }
}
