package com.softcube.re_reddit.presentation.splash

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.softcube.re_reddit.application.base.BaseViewModel
import com.softcube.re_reddit.common.ExceptionHandler
import com.softcube.re_reddit.common.Status
import com.softcube.re_reddit.data.remote.model.ApiError
import com.softcube.re_reddit.domain.model.AccessToken
import com.softcube.re_reddit.domain.usecase.AuthenticateBaseUseCase
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import java.util.*

/**
 * com.softcube.re_reddit.presentation.splash
 *
 * Created by Wilson Garcia on 6/5/21
 * Copyright © 2021 Wilson Garcia. All rights reserved.
 */
data class SplashViewState(
	var status: Status,
	var data: AccessToken?,
	var error: ApiError?
)

class SplashViewModel(private val authUseCase: AuthenticateBaseUseCase) : BaseViewModel<SplashViewState>() {

	private var authJob: Job? = null

	override val coroutineExceptionHandler = CoroutineExceptionHandler { _, exception ->
		val error = ExceptionHandler.parse(exception)
		setError(error)
	}

	init {
		stateMutableLiveData.value = SplashViewState(status = Status.NONE, data = null, error = null)
	}

	fun authenticate() {
		val deviceId = UUID.randomUUID().toString()
		stateMutableLiveData.value = stateMutableLiveData.value?.copy(status = Status.LOADING)
		authJob = launchCoroutine {
			var accessToken: AccessToken? = null
			val call = async { authUseCase.invoke(deviceId) }

			try {
				accessToken = call.await()
			} catch (ex: Exception) {
				val error = ExceptionHandler.parse(ex)
				setError(error)
			}

			setData(accessToken)
		}
	}

	private fun setError(error: ApiError?) {
		stateMutableLiveData.value = stateMutableLiveData.value?.copy(
			status = Status.ERROR,
			data = null,
			error = error
		)
	}

	private fun setData(data: AccessToken?) {
		stateMutableLiveData.value = stateMutableLiveData.value?.copy(
			status = Status.SUCCESS,
			data = data,
			error = null
		)
	}

	override fun onCleared() {
		super.onCleared()
		authJob?.cancel()
	}
}
