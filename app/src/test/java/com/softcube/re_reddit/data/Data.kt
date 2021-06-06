package com.softcube.re_reddit.data

import com.softcube.re_reddit.data.remote.model.TokenDTO
import com.softcube.re_reddit.domain.model.AccessToken

/**
 * com.softcube.re_reddit.data
 *
 * Created by Wilson Garcia on 6/5/21
 * Copyright © 2021 Wilson Garcia. All rights reserved.
 */
object Data {
	fun getToken(): TokenDTO  =
		TokenDTO(
			accessToken = "--4Kz6jnKFOpzUXgHKhQS5_E3UsUfeA",
			tokenType = "bearer",
			deviceId = "",
			expiresIn = 3600,
			scope = "*"
		)
}
