package com.softcube.re_reddit.common.extension

import android.view.View
import android.widget.ImageView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.bumptech.glide.Glide

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

fun View.visibleIf(show: Boolean) {
	this.visibility = if(show) View.VISIBLE else View.GONE
}

fun ImageView.loadUrl(url: String?) {
	this.visibleIf(!url.isNullOrBlank())
	if (!url.isNullOrBlank()) {
		Glide.with(this.context)
			.load(url)
			.into(this)
	}
}

inline fun <reified R> Any?.whatIfNotNullAs(
	whatIf: (R) -> Unit,
	whatIfNot: () -> Unit
): Any? {

	if (this != null && this is R) {
		whatIf(this as R)
		return this
	}
	whatIfNot()
	return this
}

inline fun <reified R> Any?.whatIfNotNullAs(
	whatIf: (R) -> Unit
): Any? {

	return whatIfNotNullAs(
		whatIf = whatIf,
		whatIfNot = { }
	)
}
