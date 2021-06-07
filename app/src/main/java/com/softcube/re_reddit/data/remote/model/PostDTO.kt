package com.softcube.re_reddit.data.remote.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.softcube.re_reddit.domain.model.Post
import kotlinx.parcelize.Parcelize

/**
 * com.softcube.re_reddit.data.remote.model
 *
 * Created by Wilson Garcia on 6/6/21
 * Copyright © 2021 Wilson Garcia. All rights reserved.
 */
@Parcelize
data class PostDTO(
	@SerializedName("id") val id: String,
	@SerializedName("title") val title: String,
	@SerializedName("author") val author: String,
	@SerializedName("thumbnail") val thumbnail: String?,
	@SerializedName("num_comments") val comments: Int,
	@SerializedName("created_utc") val created: Long,
	@SerializedName("url_overridden_by_dest") val url: String?,
	@SerializedName("is_video") val isVideo: Boolean,
): Entity<Post> {
	override fun toDomain(): Post = Post(
			id = id,
			title = title,
			author = author,
			thumbnail = thumbnail,
			totalComments = comments,
			created = created,
			image = if(isVideo) null else url
		)
}
