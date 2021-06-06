package com.softcube.re_reddit.presentation.post

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.softcube.re_reddit.R
import com.softcube.re_reddit.databinding.PostItemBinding
import com.softcube.re_reddit.domain.model.Post

/**
 * com.softcube.re_reddit.presentation.post
 *
 * Created by Wilson Garcia on 6/6/21
 * Copyright © 2021 Wilson Garcia. All rights reserved.
 */
typealias PostCallback = (Post) -> Unit

class PostListAdapter(private val onClickListener: PostCallback) : RecyclerView.Adapter<PostListAdapter.PostViewHolder>() {

	private val items = mutableListOf<Post>()

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
		val inflater = LayoutInflater.from(parent.context)
		val binding = DataBindingUtil.inflate<PostItemBinding>(
			inflater,
			R.layout.post_item,
			parent,
			false
		)
		return PostViewHolder(binding).apply {
			binding.root.setOnClickListener { _ ->
				val position =
					adapterPosition.takeIf { it != RecyclerView.NO_POSITION }
						?: return@setOnClickListener

				onClickListener(getPost(position))
			}
		}
	}

	fun updatePostList(posts: List<Post>) {
		items.addAll(posts)
		notifyDataSetChanged()
	}

	override fun getItemCount(): Int = items.size

	override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
		holder.binding.apply {
			post = items[position]
			executePendingBindings()
		}
	}

	private fun getPost(index: Int): Post = items[index]

	class PostViewHolder(val binding: PostItemBinding) :
		RecyclerView.ViewHolder(binding.root)
}
