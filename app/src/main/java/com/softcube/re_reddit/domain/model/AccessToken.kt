package com.softcube.re_reddit.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * com.softcube.re_reddit.domain.model
 *
 * Created by Wilson Garcia on 6/5/21
 * Copyright © 2021 Wilson Garcia. All rights reserved.
 */
@Parcelize
data class AccessToken(
	val token: String,
	val type: String,
	val expiresIn: Int
): Parcelable {
	fun getTokenHeaderValue(): String = "$type $token"
}
