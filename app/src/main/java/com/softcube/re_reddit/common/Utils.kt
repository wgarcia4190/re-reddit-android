package com.softcube.re_reddit.common

import android.app.DownloadManager
import android.content.Context
import android.net.Uri
import android.os.Environment
import android.widget.Toast
import java.io.File

/**
 * com.softcube.re_reddit.common
 *
 * Created by Wilson Garcia on 6/6/21
 * Copyright © 2021 Wilson Garcia. All rights reserved.
 */
object Utils {
	fun addImageToGallery(url: String?, fileName: String, context: Context) {
		url?.let { path ->
			try {
				val dm = context.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager?
				val downloadUri = Uri.parse(path)
				val request = DownloadManager.Request(downloadUri)
				request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI or DownloadManager.Request.NETWORK_MOBILE)
					.setAllowedOverRoaming(false)
					.setTitle(fileName)
					.setMimeType("image/jpeg")
					.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
					.setDestinationInExternalPublicDir(
						Environment.DIRECTORY_PICTURES,
						File.separator + fileName
					)
				dm?.enqueue(request)
				Toast.makeText(context, "Image download started.", Toast.LENGTH_SHORT).show()
			} catch (e: Exception) {
				e.printStackTrace()
				Toast.makeText(context, "Image download failed.", Toast.LENGTH_SHORT).show()
			}
		}
	}
}
