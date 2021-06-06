package com.softcube.re_reddit.common

import androidx.lifecycle.*

/**
 * com.softcube.re_reddit.common
 *
 * Created by Wilson Garcia on 6/5/21
 * Copyright © 2021 Wilson Garcia. All rights reserved.
 */
internal class OneTimeObserver<T>(private val handler: (T) -> Unit) : Observer<T>, LifecycleOwner {

	private val lifecycle = LifecycleRegistry(this)

	init {
		lifecycle.handleLifecycleEvent(Lifecycle.Event.ON_RESUME)
	}

	override fun getLifecycle(): Lifecycle = lifecycle

	override fun onChanged(t: T) {
		handler(t)
		lifecycle.handleLifecycleEvent(Lifecycle.Event.ON_DESTROY)
	}
}

fun <T> LiveData<T>.observeOnce(onChangeHandler: (T) -> Unit) {
	val observer = OneTimeObserver(onChangeHandler)
	// Lifecycle owner and observer
	observe(observer, observer)
}
