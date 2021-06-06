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
data class PostsResponseDTO(
	@SerializedName("kind") val kind: String,
	@SerializedName("data") val data: PostsDataDTO,
): Parcelable {
	fun getPosts(): List<Post> = data.getPosts()
}

@Parcelize
data class PostsChildrenDTO(
	@SerializedName("kind") val kind: String,
	@SerializedName("data") val data: PostDTO,
): Parcelable


@Parcelize
data class PostsDataDTO(
	@SerializedName("modhash") val hash: String,
	@SerializedName("dist") val total: Int,
	@SerializedName("children") val children: List<PostsChildrenDTO>,
	@SerializedName("after") val after: String?,
	@SerializedName("before") val before: String?,
) : Parcelable {
	fun getPosts(): List<Post> {
		val posts = mutableListOf<Post>()
		for(child in children) {
			posts.add(child.data.toDomain())
		}
		return posts
	}
}
