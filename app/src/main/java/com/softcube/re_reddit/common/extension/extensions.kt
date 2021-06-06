package com.softcube.re_reddit.common.extension

import androidx.swiperefreshlayout.widget.SwipeRefreshLayout

/**
 * com.softcube.re_reddit.common.extension
 *
 * Created by Wilson Garcia on 6/5/21
 * Copyright © 2021 Wilson Garcia. All rights reserved.
 */

const val SECOND_MILLIS: Int = 1000
const val MINUTE_MILLIS = 60 * SECOND_MILLIS
const val HOUR_MILLIS = 60 * MINUTE_MILLIS
const val DAY_MILLIS = 24 * HOUR_MILLIS

inline fun String?.guardEmpty(block: String?.() -> Unit): String {
	if (isNullOrBlank()) {
		block()
	} else {
		return this
	}

	return ""
}

fun Long.toTimeAgo(): String {
	val time = this * 1000
	val now = System.currentTimeMillis()
	val diff = now - time

	return when(true) {
		diff < MINUTE_MILLIS -> "just now"
		diff < 2 * MINUTE_MILLIS -> "a minute ago"
		diff < 50 * MINUTE_MILLIS -> "${diff.div(MINUTE_MILLIS)} minutes ago"
		diff < 90 * MINUTE_MILLIS -> "an hour ago"
		else -> "${diff.div(HOUR_MILLIS)} hours ago"
	}
}

fun SwipeRefreshLayout.toggleLoading(show: Boolean) {
	this.post {
		this.isRefreshing = show
	}
}
