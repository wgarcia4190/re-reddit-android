package com.softcube.re_reddit.data.remote.model

import android.os.Parcelable

/**
 * com.softcube.re_reddit.data.remote.model
 *
 * Created by Wilson Garcia on 6/5/21
 * Copyright © 2021 Wilson Garcia. All rights reserved.
 */
interface Entity<S>: Parcelable {
	fun toDomain(): S
}
