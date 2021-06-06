package com.softcube.re_reddit.common

import com.google.common.truth.Truth
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody.Companion.toResponseBody
import org.json.JSONObject
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException
import java.net.SocketException


/**
 * com.softcube.re_reddit.common
 *
 * Created by Wilson Garcia on 6/5/21
 * Copyright © 2021 Wilson Garcia. All rights reserved.
 */
@RunWith(JUnit4::class)
class ExceptionHandlerTest {

	@Test
	fun `given an exception when is unknown instance then get default string`() {
		val error = ExceptionHandler.parse(Exception())
		Truth.assertThat(error.message).isEqualTo(UNKNOWN_ERROR_MESSAGE)
	}

	@Test
	fun `given an exception when is SocketException instance then get default string`() {
		val error = ExceptionHandler.parse(SocketException("Timeout"))
		Truth.assertThat(error.message).isEqualTo("Timeout")
	}

	@Test
	fun `given an exception when is IOException instance then get default string`() {
		val error = ExceptionHandler.parse(IOException("No Connection"))
		Truth.assertThat(error.message).isEqualTo("No Connection")
	}

	@Test
	fun `given an exception when is HttpException instance then get default string`() {

		val responseError = Response.error<Unit>(
			400,
			"{\"error\":\"unsupported_grant_type\"}"
				.toResponseBody("application/json".toMediaTypeOrNull())
		)

		val error = ExceptionHandler.parse(HttpException(responseError))
		Truth.assertThat(error.message).isEqualTo("unsupported_grant_type")
	}
}
