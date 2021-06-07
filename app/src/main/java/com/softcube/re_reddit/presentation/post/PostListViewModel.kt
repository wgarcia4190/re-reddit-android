package com.softcube.re_reddit.presentation.post

import android.content.SharedPreferences
import com.softcube.re_reddit.application.base.BaseViewModel
import com.softcube.re_reddit.common.ExceptionHandler
import com.softcube.re_reddit.common.Status
import com.softcube.re_reddit.data.remote.model.ApiError
import com.softcube.re_reddit.domain.model.AccessToken
import com.softcube.re_reddit.domain.model.Post
import com.softcube.re_reddit.domain.usecase.GetPostsBaseUseCase
import com.softcube.re_reddit.presentation.splash.SplashViewState
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import java.util.*

/**
 * com.softcube.re_reddit.presentation.post
 *
 * Created by Wilson Garcia on 6/6/21
 * Copyright © 2021 Wilson Garcia. All rights reserved.
 */

data class PostListViewState(
	var status: Status,
	var data: List<Post>?,
	var after: String?,
	var error: ApiError?
)

class PostListViewModel(private val postsUseCase: GetPostsBaseUseCase, private val sharedPreferences: SharedPreferences) : BaseViewModel<PostListViewState>() {

	private var postsJob: Job? = null
	val count: Int
		get() = stateLiveData.value?.data?.size ?: 0

	override val coroutineExceptionHandler = CoroutineExceptionHandler { _, exception ->
		val error = ExceptionHandler.parse(exception)
		setError(error)
	}

	init {
		stateMutableLiveData.value = PostListViewState(status = Status.NONE, data = null, after = null, error = null)
	}

	fun getPosts() {
		stateMutableLiveData.value = stateMutableLiveData.value?.copy(status = Status.LOADING)
		postsJob = launchCoroutine {
			var posts: List<Post>? = null
			var after: String? = null
			val call = async { postsUseCase.invoke(stateMutableLiveData.value?.after ?: "") }

			try {
				val response = call.await()
				posts = response.posts
				after = response.after
			} catch (ex: Exception) {
				val error = ExceptionHandler.parse(ex)
				setError(error)
			}

			setData(posts, after)
		}
	}

	fun refresh() {
		stateMutableLiveData.value = stateMutableLiveData.value?.copy(
			status = Status.NONE,
			data = null,
			after = null,
			error = null
		)
		getPosts()
	}

	private fun setError(error: ApiError?) {
		stateMutableLiveData.value = stateMutableLiveData.value?.copy(
			status = Status.ERROR,
			error = error
		)
	}

	private fun setData(data: List<Post>?, after: String?) {
		val mutablePost = mutableListOf<Post>()
		stateMutableLiveData.value?.data?.let {
			mutablePost.addAll(it)
		}

		data?.let {
			mutablePost.addAll(it)
		}

		for(post in mutablePost) {
			post.clicked = sharedPreferences.getBoolean(post.id, false)
		}

		stateMutableLiveData.value = stateMutableLiveData.value?.copy(
			status = Status.SUCCESS,
			data = mutablePost,
			after = after,
			error = null
		)
	}

	override fun onCleared() {
		super.onCleared()
		postsJob?.cancel()
	}
}
