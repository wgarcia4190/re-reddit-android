package com.softcube.re_reddit.presentation.post

import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.softcube.re_reddit.application.base.BaseFragment
import com.softcube.re_reddit.common.Status
import com.softcube.re_reddit.common.UNKNOWN_ERROR_MESSAGE
import com.softcube.re_reddit.common.autoCleared
import com.softcube.re_reddit.common.extension.toggleLoading
import com.softcube.re_reddit.common.extension.whatIfNotNullAs
import com.softcube.re_reddit.databinding.FragmentPostListBinding
import com.softcube.re_reddit.domain.model.Post
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * com.softcube.re_reddit.presentation.post
 *
 * Created by Wilson Garcia on 6/6/21
 * Copyright © 2021 Wilson Garcia. All rights reserved.
 */
class PostListFragment: BaseFragment() {

	private var binding by autoCleared<FragmentPostListBinding>()
	private val viewModel: PostListViewModel by viewModel()
	private val sharedPreferencesEditor: SharedPreferences.Editor by inject()

	override fun onCreateView(
		inflater: LayoutInflater,
		container: ViewGroup?,
		savedInstanceState: Bundle?
	): View {
		binding = FragmentPostListBinding.inflate(inflater, container, false)
		binding.lifecycleOwner = this

		observeViewState()
		viewModel.getPosts()

		setupUI()

		return binding.root
	}

	private fun setupUI() {
		binding.pullToRefresh.setOnRefreshListener {
			viewModel.refresh()
		}

		binding.postList.apply {
			adapter = PostListAdapter{ post ->
				sharedPreferencesEditor.putBoolean(post.id, true).apply()
				navigateTo(PostListFragmentDirections.toPostDetails(post))
			}

			addOnScrollListener(object : RecyclerView.OnScrollListener(){
				override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
					super.onScrollStateChanged(recyclerView, newState)

					if (!recyclerView.canScrollVertically(1) && newState==RecyclerView.SCROLL_STATE_IDLE && viewModel.count < 50) {
						viewModel.getPosts()
					}
				}
			})
		}
	}

	private fun observeViewState() {
		viewModel.stateLiveData.observe(
			viewLifecycleOwner,
			Observer { state ->
				when (state.status) {
					Status.LOADING -> binding.pullToRefresh.toggleLoading(true)
					Status.SUCCESS -> {
						binding.pullToRefresh.toggleLoading(false)
						loadData(state.data)

					}
					Status.ERROR -> {
						binding.pullToRefresh.toggleLoading(false)
						state.error?.let { Log.e("PostListFragment", it.message ?: UNKNOWN_ERROR_MESSAGE) }
					}
					else -> print("NONE")
				}
			}
		)
	}

	private fun loadData(posts: List<Post>?) {
		posts?.let { data ->
			Log.d("PostListFragment", data.size.toString())
			binding.postList.apply {
				adapter.whatIfNotNullAs<PostListAdapter> {
					it.updatePostList(data)
				}
			}
		}
	}
}
