package com.adyen.android.assignment.features.general_errors

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ArgumentData(
    val mainText: String,
    val subTitle: String,
    val actionBtnText: String,
    val onClick: () -> Unit
) :
    Parcelable