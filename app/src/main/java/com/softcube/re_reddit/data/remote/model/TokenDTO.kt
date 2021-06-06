package com.softcube.re_reddit.data.remote.model

import com.google.gson.annotations.SerializedName
import com.softcube.re_reddit.domain.model.AccessToken
import kotlinx.parcelize.Parcelize

/**
 * com.softcube.re_reddit.data.remote.model
 *
 * Created by Wilson Garcia on 6/5/21
 * Copyright © 2021 Wilson Garcia. All rights reserved.
 */
@Parcelize
data class TokenDTO(
	@SerializedName("access_token") val accessToken: String,
	@SerializedName("token_type") val tokenType: String,
	@SerializedName("device_id") val deviceId: String,
	@SerializedName("expires_in") val expiresIn: Int,
	@SerializedName("scope") val scope: String,
): Entity<AccessToken> {

	override fun toDomain(): AccessToken = AccessToken(
		token = accessToken,
		type = tokenType,
		expiresIn = expiresIn
	)
}
