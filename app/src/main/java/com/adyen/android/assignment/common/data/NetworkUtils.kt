package com.adyen.android.assignment.common.data

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class NetworkUtils @Inject constructor(@ApplicationContext val context: Context) :
    ConnectivityManager.NetworkCallback() {

    private val _networkLiveData: MutableLiveData<Boolean> = MutableLiveData()
    val networkLiveData: LiveData<Boolean> get() = _networkLiveData

    fun getNetworkStatus(): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        connectivityManager.registerDefaultNetworkCallback(this)

        var isConnected = false

        connectivityManager.allNetworks.forEach { network ->
            val networkCapability = connectivityManager.getNetworkCapabilities(network)

            networkCapability?.let {
                if (it.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)) {
                    isConnected = true
                    return@forEach
                }
            }
        }

        return isConnected
    }

    override fun onAvailable(network: Network) {
        _networkLiveData.postValue(true)
    }

    override fun onLost(network: Network) {
        _networkLiveData.postValue(false)
    }

}