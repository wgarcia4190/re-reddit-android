package com.softcube.re_reddit.common

import com.bumptech.glide.RequestManager
import org.koin.core.KoinComponent
import org.koin.core.inject

/**
 * com.softcube.re_reddit.common
 *
 * Created by Wilson Garcia on 6/6/21
 * Copyright © 2021 Wilson Garcia. All rights reserved.
 */
object GlideInstance : KoinComponent {
	val glide: RequestManager by inject()
}
