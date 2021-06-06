package com.softcube.re_reddit.data.repository

import com.softcube.re_reddit.data.remote.api.PostService
import com.softcube.re_reddit.domain.model.PostResponse
import com.softcube.re_reddit.domain.repository.PostRepository

/**
 * com.softcube.re_reddit.data.repository
 *
 * Created by Wilson Garcia on 6/6/21
 * Copyright © 2021 Wilson Garcia. All rights reserved.
 */
class PostRepositoryImpl(private val service: PostService) : PostRepository {

	override suspend fun getPosts(after: String): PostResponse =
		service.getTopPost(after = after).toDomain()
}
