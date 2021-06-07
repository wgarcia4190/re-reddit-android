package com.softcube.re_reddit.application.di

import android.app.Application
import android.content.SharedPreferences
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

/**
 * com.softcube.re_reddit.application.di
 *
 * Created by Wilson Garcia on 6/6/21
 * Copyright © 2021 Wilson Garcia. All rights reserved.
 */
val appModule = module {

	single { provideSharedPrefs(androidApplication()) }
	single { provideGlide(androidApplication()) }
	single<SharedPreferences.Editor> { provideSharedPrefs(androidApplication()).edit() }

}

internal fun provideSharedPrefs(androidApplication: Application): SharedPreferences {
	return androidApplication.getSharedPreferences(
		androidApplication.packageName,
		android.content.Context.MODE_PRIVATE
	)
}

internal fun provideGlide(androidApplication: Application): RequestManager =
	Glide.with(androidApplication)
