package com.adyen.android.assignment.common

import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

const val TIME_OUT_IN_SECONDS = 30L
const val IMAGE_SIZE_MULTIPLIER = 0.1f
const val ERROR_KEY = "error_key"
const val ERROR_NAVIGATION_ARGUMENTS = "error_navigation_args"



fun Fragment.navigateUp() { //TODO move to extensions
    findNavController().navigateUp()
}