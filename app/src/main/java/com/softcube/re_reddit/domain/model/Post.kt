package com.softcube.re_reddit.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * com.softcube.re_reddit.domain.model
 *
 * Created by Wilson Garcia on 6/6/21
 * Copyright © 2021 Wilson Garcia. All rights reserved.
 */
@Parcelize
data class Post(
	val title: String,
	val author: String,
	val created: Long,
	val thumbnail: String?,
	val image: String?,
	val totalComments: Int,
	val clicked: Boolean = false
): Parcelable {
	fun hasImage(): Boolean = image != null
}
