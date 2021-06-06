package com.softcube.re_reddit.data.remote.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * com.softcube.re_reddit.data.remote.model
 *
 * Created by Wilson Garcia on 6/5/21
 * Copyright © 2021 Wilson Garcia. All rights reserved.
 */
data class ApiError(
	override val cause: Throwable? = null,
	override val message: String?,
) : Exception(message, cause) {

	override fun toString(): String {
		return "[message: $message - cause: ${cause?.message}]"
	}
}
