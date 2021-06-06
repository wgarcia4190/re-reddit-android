package com.softcube.re_reddit.common

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.softcube.re_reddit.common.extension.loadUrl

/**
 * com.softcube.re_reddit.common
 *
 * Created by Wilson Garcia on 6/6/21
 * Copyright © 2021 Wilson Garcia. All rights reserved.
 */
object BindingAdapters {
	@BindingAdapter("loadUrl")
	@JvmStatic
	fun loadUrl(image: ImageView, url: String?) {
		image.loadUrl(url)
	}
}
