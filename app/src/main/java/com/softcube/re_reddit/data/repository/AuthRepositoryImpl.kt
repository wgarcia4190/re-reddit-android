package com.softcube.re_reddit.data.repository

import com.softcube.re_reddit.data.remote.api.AuthService
import com.softcube.re_reddit.domain.model.AccessToken
import com.softcube.re_reddit.domain.repository.AuthRepository

/**
 * com.softcube.re_reddit.data.repository
 *
 * Created by Wilson Garcia on 6/5/21
 * Copyright © 2021 Wilson Garcia. All rights reserved.
 */
class AuthRepositoryImpl(private val service: AuthService) : AuthRepository {

	override suspend fun authenticate(deviceId: String): AccessToken =
		service.authenticate(deviceId).toDomain()
}
