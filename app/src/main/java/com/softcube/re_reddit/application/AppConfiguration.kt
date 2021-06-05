package com.softcube.re_reddit.application

import com.softcube.re_reddit.BuildConfig

/**
 * com.softcube.re_reddit.application
 *
 * Created by Wilson Garcia on 6/5/21
 * Copyright © 2021 Wilson Garcia. All rights reserved.
 */
object AppConfiguration {
	val apiAuthURL: String
		get() = BuildConfig.AUTH_URL

	val apiDataURL: String
		get() = BuildConfig.DATA_URL

	val basicAuthUser: String
		get() = BuildConfig.CLIENT_ID

	val basicAuthPassword: String
		get() = BuildConfig.PASSWORD

	val grantType: String
		get() = BuildConfig.GRANT_TYPE
}
