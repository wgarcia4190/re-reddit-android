package com.softcube.re_reddit.domain.model

import android.os.Parcelable
import com.softcube.re_reddit.common.extension.toTimeAgo
import kotlinx.parcelize.Parcelize

/**
 * com.softcube.re_reddit.domain.model
 *
 * Created by Wilson Garcia on 6/6/21
 * Copyright © 2021 Wilson Garcia. All rights reserved.
 */
@Parcelize
data class Post(
	val id: String,
	val title: String,
	val author: String,
	val created: Long,
	val thumbnail: String?,
	val image: String?,
	val totalComments: Int,
	var clicked: Boolean = false
): Parcelable {
	fun hasImage(): Boolean = image != null
	fun getTime(): String = created.toTimeAgo()
}

@Parcelize
data class PostResponse(
	val posts: List<Post>,
	val after: String
): Parcelable
