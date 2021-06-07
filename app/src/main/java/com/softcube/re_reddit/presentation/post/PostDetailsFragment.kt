package com.softcube.re_reddit.presentation.post

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.softcube.re_reddit.R
import com.softcube.re_reddit.application.base.BaseFragment
import com.softcube.re_reddit.common.Utils
import com.softcube.re_reddit.common.autoCleared
import com.softcube.re_reddit.common.extension.clearDrawables
import com.softcube.re_reddit.common.extension.visibleIf
import com.softcube.re_reddit.databinding.FragmentPostDetailsBinding

/**
 * com.softcube.re_reddit.presentation.post
 *
 * Created by Wilson Garcia on 6/6/21
 * Copyright © 2021 Wilson Garcia. All rights reserved.
 */
class PostDetailsFragment: BaseFragment(), PopupMenu.OnMenuItemClickListener {

	private var binding by autoCleared<FragmentPostDetailsBinding>()
	private val safeArgs by navArgs<PostDetailsFragmentArgs>()

	override fun onCreateView(
		inflater: LayoutInflater,
		container: ViewGroup?,
		savedInstanceState: Bundle?
	): View {
		binding = FragmentPostDetailsBinding.inflate(inflater, container, false)
		binding.lifecycleOwner = this

		binding.postData.post = safeArgs.Post

		setupUI()

		return binding.root
	}

	private fun setupUI() {
		retainInstance = true
		binding.backButton.setOnClickListener {
			back()
		}
		binding.menu.setOnClickListener {
			showPopup(it)
		}
		binding.postData.options.visibleIf(false)
		binding.postData.dismissPost.visibleIf(false)
		binding.postData.status.clearDrawables()
		binding.menu.visibleIf(safeArgs.Post.hasImage())
	}

	private fun showPopup(it: View) {
		val popup = PopupMenu(it.context, it)
		popup.setOnMenuItemClickListener(this)
		popup.inflate(R.menu.post_list_menu)
		popup.menu.findItem(R.id.dismiss_post).isVisible = false
		popup.show()
	}

	override fun onMenuItemClick(item: MenuItem?): Boolean {
		return when (item?.itemId) {

			R.id.download -> {
				safeArgs.Post.image?.let { image ->
					Utils.addImageToGallery(image, safeArgs.Post.id, requireActivity())
				}
				true
			}
			else -> false
		}
	}
}
