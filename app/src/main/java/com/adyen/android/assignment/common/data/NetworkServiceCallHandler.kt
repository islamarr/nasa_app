package com.adyen.android.assignment.common.data

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import retrofit2.HttpException

/**
 * To handle Http Exceptions Like no internet connection and Time out
 */
interface NetworkServiceCallHandler {

    suspend fun <T> safeApiCall(
        dispatcher: CoroutineDispatcher,
        apiCall: suspend () -> T
    ): NetworkResponse<T> {
        return withContext(dispatcher) {
            try {
                NetworkResponse.Success(apiCall.invoke())
            } catch (throwable: Throwable) {
                when (throwable) {
                    is HttpException -> NetworkResponse.Failure(
                        throwable.response()?.errorBody().toString()
                    )
                    is NoInternetException -> NetworkResponse.NoInternet
                    else -> NetworkResponse.Failure(throwable.message)
                }
            }
        }
    }

}