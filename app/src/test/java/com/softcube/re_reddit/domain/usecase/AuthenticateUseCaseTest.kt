package com.softcube.re_reddit.domain.usecase

import com.softcube.re_reddit.application.base.BaseUseCaseTest
import com.softcube.re_reddit.application.base.NetworkResult
import com.softcube.re_reddit.common.UNKNOWN_ERROR_MESSAGE
import com.softcube.re_reddit.data.Data
import com.softcube.re_reddit.data.remote.model.ApiError
import com.softcube.re_reddit.domain.model.AccessToken

/**
 * com.softcube.re_reddit.domain.usecase
 *
 * Created by Wilson Garcia on 6/5/21
 * Copyright © 2021 Wilson Garcia. All rights reserved.
 */
class AuthenticateUseCaseTest: BaseUseCaseTest<String, AccessToken>(), AuthenticateBaseUseCase{
	override fun getValue(params: String): AccessToken {
		return when (result) {
			NetworkResult.SUCCESS -> Data.getToken().toDomain()
			else -> throw ApiError(message = UNKNOWN_ERROR_MESSAGE)
		}
	}

	override suspend fun invoke(params: String): AccessToken = execute(params)



}
