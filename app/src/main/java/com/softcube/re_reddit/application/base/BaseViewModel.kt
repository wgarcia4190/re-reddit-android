package com.softcube.re_reddit.application.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

/**
 * com.softcube.re_reddit.application.base
 *
 * Created by Wilson Garcia on 6/5/21
 * Copyright © 2021 Wilson Garcia. All rights reserved.
 */
abstract class BaseViewModel: ViewModel() {

	abstract val coroutineExceptionHandler: CoroutineExceptionHandler

	protected fun launchCoroutine(block: suspend CoroutineScope.() -> Unit): Job {
		return viewModelScope.launch(coroutineExceptionHandler) {
			block()
		}
	}
}
