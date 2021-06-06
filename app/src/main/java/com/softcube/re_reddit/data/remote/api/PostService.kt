package com.softcube.re_reddit.data.remote.api

import com.softcube.re_reddit.application.AppConfiguration
import com.softcube.re_reddit.data.remote.model.TokenDTO
import retrofit2.http.Field
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * com.softcube.re_reddit.data.remote.api
 *
 * Created by Wilson Garcia on 6/6/21
 * Copyright © 2021 Wilson Garcia. All rights reserved.
 */
interface PostService {
	@GET("top")
	suspend fun getTopPost(
		@Query("t") time: String = "hour",
		@Query("limit") limit: Int = 10,
		@Query("after") after: String = ""
	): TokenDTO
}
