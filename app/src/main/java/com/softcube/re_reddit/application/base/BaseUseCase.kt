package com.softcube.re_reddit.application.base

/**
 * com.softcube.re_reddit.application.base
 *
 * Created by Wilson Garcia on 6/5/21
 * Copyright © 2021 Wilson Garcia. All rights reserved.
 */
interface BaseUseCase<in Parameter, out Result> {
	suspend operator fun invoke(params: Parameter): Result
}
