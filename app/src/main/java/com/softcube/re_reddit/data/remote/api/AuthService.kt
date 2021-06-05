package com.softcube.re_reddit.data.remote.api

import com.softcube.re_reddit.application.AppConfiguration
import com.softcube.re_reddit.data.remote.model.TokenDTO
import retrofit2.http.*

/**
 * com.softcube.re_reddit.data.remote.api
 *
 * Created by Wilson Garcia on 6/5/21
 * Copyright © 2021 Wilson Garcia. All rights reserved.
 */
interface AuthService {

	@FormUrlEncoded
	@POST("api/v1/access_token")
	suspend fun authenticate(
		@Field("device_id") deviceId: String,
		@Field("grant_type") grantType: String = AppConfiguration.grantType
	): TokenDTO
}
