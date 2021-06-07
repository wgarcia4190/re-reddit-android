package com.softcube.re_reddit.presentation.post

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.PopupMenu
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
typealias DownloadCallback = (String, String) -> Unit

class PostListAdapter(
	private val downloadCallback: DownloadCallback,
	private val onClickListener: PostCallback
) : RecyclerView.Adapter<PostListAdapter.PostViewHolder>() {

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
			binding.container.setOnClickListener {
				showPostDetails()
			}
			binding.title.setOnClickListener {
				showPostDetails()
			}
		}
	}

	private fun PostViewHolder.showPostDetails() {
		val position =
			adapterPosition.takeIf { it != RecyclerView.NO_POSITION }
				?: return

		val post = getPost(position)
		post.clicked = true

		onClickListener(post)
		notifyItemChanged(position)
	}

	fun updatePostList(posts: List<Post>) {
		items.addAll(posts)
		notifyDataSetChanged()
	}

	override fun getItemCount(): Int = items.size

	override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
		val postObj = items[position]
		holder.binding.apply {
			post = postObj
			options.setOnClickListener {
				val popup = PopupMenu(it.context, it)
				popup.setOnMenuItemClickListener { item ->
					when (item?.itemId) {
						R.id.dismiss_post ->
							true
						R.id.download -> {
							postObj.image?.let { image ->
								downloadCallback(image, postObj.id)
							}
							true
						}
						else -> false
					}
				}
				popup.inflate(R.menu.post_list_menu)
				popup.menu.findItem(R.id.download).isVisible = postObj.hasImage()
				popup.show()
			}

			executePendingBindings()
		}
	}


	private fun getPost(index: Int): Post = items[index]

	class PostViewHolder(val binding: PostItemBinding) :
		RecyclerView.ViewHolder(binding.root)
}
