package com.softcube.re_reddit.presentation.post

import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.PopupMenu
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.softcube.re_reddit.R
import com.softcube.re_reddit.common.extension.clearDrawables
import com.softcube.re_reddit.databinding.PostItemBinding
import com.softcube.re_reddit.domain.model.Post


/**
 * com.softcube.re_reddit.presentation.post
 *
 * Created by Wilson Garcia on 6/6/21
 * Copyright © 2021 Wilson Garcia. All rights reserved.
 */
typealias PostCallback = (Post) -> Unit
typealias DeleteCallback = (Int) -> Unit
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
		return PostViewHolder(binding, downloadCallback) { pos ->
			items.removeAt(pos)
			notifyItemRemoved(pos)
		}.apply {
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

	fun deleteAll() {
		items.clear()
		notifyDataSetChanged()
	}

	override fun getItemCount(): Int = items.size

	override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
		holder.bind(getPost(position))
	}


	private fun getPost(index: Int): Post = items[index]

	class PostViewHolder(
		private val binding: PostItemBinding,
		private val downloadCallback: DownloadCallback,
		private val deleteCallback: DeleteCallback
	) :
		RecyclerView.ViewHolder(binding.root), PopupMenu.OnMenuItemClickListener {

		private lateinit var postObj: Post

		fun bind(postObj: Post) {
			this.postObj = postObj
			binding.apply {
				post = postObj
				options.setOnClickListener {
					showPopup(it)
				}
				dismissPost.setOnClickListener {
					deleteItem()
				}

				if(postObj.clicked)
					status.clearDrawables()
				executePendingBindings()
			}
		}

		private fun showPopup(it: View) {
			val popup = PopupMenu(it.context, it)
			popup.setOnMenuItemClickListener(this@PostViewHolder)
			popup.inflate(R.menu.post_list_menu)
			popup.menu.findItem(R.id.download).isVisible = postObj.hasImage()
			popup.show()
		}

		private fun deleteItem() {
			val anim: Animation = AnimationUtils.loadAnimation(
				binding.root.context,
				android.R.anim.slide_out_right
			)
			anim.duration = 300
			binding.root.startAnimation(anim)
			Handler(Looper.getMainLooper()).postDelayed({
				deleteCallback(adapterPosition)
			}, anim.duration)

		}

		override fun onMenuItemClick(item: MenuItem?): Boolean {
			return when (item?.itemId) {
				R.id.dismiss_post -> {
					deleteItem()
					true
				}
				R.id.download -> {
					postObj.image?.let { image ->
						downloadCallback(image, postObj.id)
					}
					true
				}
				else -> false
			}
		}
	}
}
