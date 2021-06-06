package com.softcube.re_reddit.common

import com.softcube.re_reddit.common.extension.guardEmpty
import com.softcube.re_reddit.data.remote.model.ApiError
import org.json.JSONObject
import retrofit2.HttpException
import java.io.IOException
import java.net.SocketTimeoutException

/**
 * com.softcube.re_reddit.common
 *
 * Created by Wilson Garcia on 6/5/21
 * Copyright © 2021 Wilson Garcia. All rights reserved.
 */

const val UNKNOWN_ERROR_MESSAGE = "Unknown Error!"

internal object ExceptionHandler {

	fun parse(throwable: Throwable?): ApiError {
		return when (throwable) {
			is ApiError -> throwable

			is HttpException -> {
				convertErrorBody(throwable)
			}

			is SocketTimeoutException -> {
				ApiError(throwable, throwable.message)
			}

			is IOException -> {
				ApiError(throwable, throwable.message)
			}

			else -> ApiError(
				throwable, UNKNOWN_ERROR_MESSAGE
			)
		}
	}

	private fun convertErrorBody(throwable: HttpException): ApiError {
		val jsonError = throwable.response()?.errorBody()?.string().guardEmpty {
			return ApiError(
				message = UNKNOWN_ERROR_MESSAGE,
				cause = throwable
			)
		}

		val jsonObject = JSONObject(jsonError)

		return ApiError(
			cause = throwable,
			message = jsonObject.optString("error")
		)
	}
}
