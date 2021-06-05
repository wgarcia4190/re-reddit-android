package com.softcube.re_reddit.domain.repository

import com.softcube.re_reddit.domain.model.AccessToken

/**
 * com.softcube.re_reddit.domain.repository
 *
 * Created by Wilson Garcia on 6/5/21
 * Copyright © 2021 Wilson Garcia. All rights reserved.
 */
interface AuthRepository {
	suspend fun authenticate(deviceId: String): AccessToken
}
