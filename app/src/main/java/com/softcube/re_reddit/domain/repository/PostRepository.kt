package com.softcube.re_reddit.domain.repository

import com.softcube.re_reddit.domain.model.AccessToken
import com.softcube.re_reddit.domain.model.Post

/**
 * com.softcube.re_reddit.domain.repository
 *
 * Created by Wilson Garcia on 6/6/21
 * Copyright © 2021 Wilson Garcia. All rights reserved.
 */
interface PostRepository {
	suspend fun getPosts(after: String = ""): List<Post>
}
