package com.softcube.re_reddit.application.base

import com.softcube.re_reddit.data.remote.model.ApiError
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * com.softcube.re_reddit.application.base
 *
 * Created by Wilson Garcia on 6/5/21
 * Copyright © 2021 Wilson Garcia. All rights reserved.
 */
abstract class BaseUseCaseTest<in P, out T>() {

	var result: NetworkResult = NetworkResult.SUCCESS

	fun execute(params: P): T {
		when (result) {
			NetworkResult.SUCCESS -> return getValue(params)
			NetworkResult.ERROR -> throw ApiError(null, "Something went wrong")
		}
	}

	abstract fun getValue(params: P): T
}
