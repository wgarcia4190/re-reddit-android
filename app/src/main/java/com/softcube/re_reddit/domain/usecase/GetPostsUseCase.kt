package com.softcube.re_reddit.domain.usecase

import com.softcube.re_reddit.application.base.BaseUseCase
import com.softcube.re_reddit.domain.model.AccessToken
import com.softcube.re_reddit.domain.model.PostResponse
import com.softcube.re_reddit.domain.repository.AuthRepository
import com.softcube.re_reddit.domain.repository.PostRepository

/**
 * com.softcube.re_reddit.domain.usecase
 *
 * Created by Wilson Garcia on 6/6/21
 * Copyright © 2021 Wilson Garcia. All rights reserved.
 */

typealias GetPostsBaseUseCase = BaseUseCase<String, PostResponse>

class GetPostsUseCase(private val repository: PostRepository) : GetPostsBaseUseCase {
	override suspend fun invoke(params: String): PostResponse = repository.getPosts(params)

}
