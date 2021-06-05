package com.softcube.re_reddit.application.network

import okhttp3.Credentials
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

/**
 * com.softcube.re_reddit.application.network
 *
 * Created by Wilson Garcia on 6/5/21
 * Copyright © 2021 Wilson Garcia. All rights reserved.
 */
class BasicAuthInterceptor(private val user: String, private val password: String): Interceptor {

	private var credentials: String = Credentials.basic(user, password)


	override fun intercept(chain: Interceptor.Chain): Response {
		val request: Request = chain.request()
		val authenticatedRequest: Request = request.newBuilder()
			.header("Authorization", credentials).build()
		return chain.proceed(authenticatedRequest)
	}


}
