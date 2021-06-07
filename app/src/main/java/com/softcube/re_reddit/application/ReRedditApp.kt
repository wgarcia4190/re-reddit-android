package com.softcube.re_reddit.application

import android.app.Application
import com.softcube.re_reddit.application.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

/**
 * com.softcube.re_reddit.application
 *
 * Created by Wilson Garcia on 6/5/21
 * Copyright © 2021 Wilson Garcia. All rights reserved.
 *
 *
 */
internal class ReRedditApp: Application() {

	companion object {
		lateinit var instance: ReRedditApp
	}

	override fun onCreate() {
		super.onCreate()

		startKoin {
			androidContext(this@ReRedditApp)
			modules(
				appModule,
				networkModule,
				useCasesModule,
				repositoryModule,
				viewModelModule
			)
		}

		instance = this
	}
}
