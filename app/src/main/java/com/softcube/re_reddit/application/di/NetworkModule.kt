package com.softcube.re_reddit.application.di

import com.softcube.re_reddit.application.AppConfiguration
import com.softcube.re_reddit.application.network.BasicAuthInterceptor
import com.softcube.re_reddit.application.network.BearerAuthInterceptor
import com.softcube.re_reddit.data.remote.api.AuthService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * com.softcube.re_reddit.application.di
 *
 * Created by Wilson Garcia on 6/5/21
 * Copyright © 2021 Wilson Garcia. All rights reserved.
 */
val networkModule = module {
	single(named("dataRetrofit")) { provideRetrofit(okHttpClient = get(named("dataHttp")), url = AppConfiguration.apiDataURL) }
	single(named("authRetrofit")) { provideRetrofit(okHttpClient = get(named("authHttp")), url = AppConfiguration.apiAuthURL) }
	single(named("dataHttp")) { provideDataOkHttpClient() }
	single(named("authHttp")) { provideBasicAuthOkHttpClient() }
	single { provideAuthenticationService(retrofit = get(named("authRetrofit"))) }
}

internal fun provideBasicAuthOkHttpClient(): OkHttpClient {
	val httpLoggingInterceptor = HttpLoggingInterceptor().apply {
		level = HttpLoggingInterceptor.Level.BODY
	}
	return OkHttpClient.Builder()
		.connectTimeout(60L, TimeUnit.SECONDS)
		.readTimeout(60L, TimeUnit.SECONDS)
		.addInterceptor(httpLoggingInterceptor)
		.addInterceptor(BasicAuthInterceptor(
			user = AppConfiguration.basicAuthUser,
			password = AppConfiguration.basicAuthPassword
		))
		.build()
}

internal fun provideDataOkHttpClient(): OkHttpClient {
	val httpLoggingInterceptor = HttpLoggingInterceptor().apply {
		level = HttpLoggingInterceptor.Level.BODY
	}
	return OkHttpClient.Builder()
		.connectTimeout(60L, TimeUnit.SECONDS)
		.readTimeout(60L, TimeUnit.SECONDS)
		.addInterceptor(httpLoggingInterceptor)
		.addInterceptor(BearerAuthInterceptor(
			token = ""
		))
		.build()
}

internal fun provideRetrofit(okHttpClient: OkHttpClient, url: String): Retrofit {
	return Retrofit.Builder()
		.baseUrl(url)
		.client(okHttpClient)
		.addConverterFactory(GsonConverterFactory.create())
		.build()
}

internal fun provideAuthenticationService(retrofit: Retrofit): AuthService =
	retrofit.create(AuthService::class.java)
