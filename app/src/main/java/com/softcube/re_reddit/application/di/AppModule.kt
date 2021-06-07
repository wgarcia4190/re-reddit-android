package com.softcube.re_reddit.application.di

import android.app.Application
import android.content.SharedPreferences
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

/**
 * com.softcube.re_reddit.application.di
 *
 * Created by Wilson Garcia on 6/6/21
 * Copyright © 2021 Wilson Garcia. All rights reserved.
 */
val appModule = module {

	single{
		getSharedPrefs(androidApplication())
	}

	single<SharedPreferences.Editor> {
		getSharedPrefs(androidApplication()).edit()
	}

}
internal fun getSharedPrefs(androidApplication: Application): SharedPreferences{
	return  androidApplication.getSharedPreferences(androidApplication.packageName,  android.content.Context.MODE_PRIVATE)
}
