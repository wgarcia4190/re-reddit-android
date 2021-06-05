package com.softcube.re_reddit.application.base

import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController

/**
 * com.softcube.re_reddit.application.base
 *
 * Created by Wilson Garcia on 6/5/21
 * Copyright © 2021 Wilson Garcia. All rights reserved.
 *
 * Wrapper all the {@link android.app.Fragment} that are going to be used
 * in the application.
 *
 * Provides utility methods for common tasks like navigation.
 */
open class BaseFragment: Fragment() {
	/**
	 * Navigate via the given {@link NavDirections}
	 *
	 * @param direction direction that describe this navigation operation
	 */
	fun navigateTo(direction: NavDirections) = findNavController().navigate(direction)

	/**
	 * Attempts to pop the controller's back stack.
	 *
	 * @return true if the stack was popped and the user has been navigated to another
	 * destination, false otherwise
	 */
	fun back() = findNavController().popBackStack()
}
