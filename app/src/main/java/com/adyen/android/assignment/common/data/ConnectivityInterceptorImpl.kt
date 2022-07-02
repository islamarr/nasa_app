package com.adyen.android.assignment.common.data

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import javax.inject.Inject

class ConnectivityInterceptorImpl @Inject constructor(private val networkUtils: NetworkUtils) :
    Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        if (!networkUtils.getNetworkStatus()) throw NoInternetException()
        val request: Request = chain.request()

        val newRequest: Request = request.newBuilder()
            .build()
        return chain.proceed(newRequest)

    }

}