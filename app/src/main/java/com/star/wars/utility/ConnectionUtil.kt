package com.star.wars.utility

import android.net.ConnectivityManager

class ConnectionUtil(private val manger: ConnectivityManager) {
    fun isNetworkInterfaceAvailable(): Boolean {
        val activeNetwork = manger.activeNetworkInfo
        return activeNetwork != null && activeNetwork.isConnectedOrConnecting
    }
}