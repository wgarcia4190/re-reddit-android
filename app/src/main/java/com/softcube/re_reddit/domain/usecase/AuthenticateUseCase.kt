package com.softcube.re_reddit.domain.usecase

import com.softcube.re_reddit.application.base.BaseUseCase
import com.softcube.re_reddit.domain.model.AccessToken
import com.softcube.re_reddit.domain.repository.AuthRepository

/**
 * com.softcube.re_reddit.domain.usecase
 *
 * Created by Wilson Garcia on 6/5/21
 * Copyright © 2021 Wilson Garcia. All rights reserved.
 */

typealias AuthenticateBaseUseCase = BaseUseCase<String, AccessToken>

class AuthenticateUseCase(private val repository: AuthRepository) : AuthenticateBaseUseCase {
	override suspend fun invoke(deviceId: String): AccessToken = repository.authenticate(deviceId)
}
