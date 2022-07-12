package com.adyen.android.assignment.common

import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

fun Fragment.navigateUp() {
    findNavController().navigateUp()
}