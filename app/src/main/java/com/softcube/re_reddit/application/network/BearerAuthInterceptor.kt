package com.softcube.re_reddit.application.network

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

/**
 * com.softcube.re_reddit.application.network
 *
 * Created by Wilson Garcia on 6/5/21
 * Copyright © 2021 Wilson Garcia. All rights reserved.
 */
class BearerAuthInterceptor(private val token: String): Interceptor {

	override fun intercept(chain: Interceptor.Chain): Response {
		val request: Request = chain.request()
		val authenticatedRequest: Request = request.newBuilder()
			.header("Authorization", token).build()
		return chain.proceed(authenticatedRequest)
	}
}
