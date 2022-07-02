package com.adyen.android.assignment.common.data


sealed class NetworkResponse<out T> {
    data class Success<T>(
        val data: T?,
    ) : NetworkResponse<T>()

    data class Failure<T>(
        val reason: String? = null,
    ) : NetworkResponse<T>()

    object NoInternet : NetworkResponse<Nothing>()
}