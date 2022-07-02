package com.adyen.android.assignment.features.general_errors

import android.os.Parcelable
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize

@Parcelize
data class ArgumentData(
    val mainText: String,
    val subTitle: String,
    val actionBtnText: String,
    val typeId : Int
) :
    Parcelable