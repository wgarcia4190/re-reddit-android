package com.softcube.re_reddit.common.extension

/**
 * com.softcube.re_reddit.common.extension
 *
 * Created by Wilson Garcia on 6/5/21
 * Copyright © 2021 Wilson Garcia. All rights reserved.
 */
inline fun String?.guardEmpty(block: String?.() -> Unit): String {
	if (isNullOrBlank()) {
		block()
	} else {
		return this
	}

	return ""
}
