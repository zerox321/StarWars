package com.star.wars.datasource.remote.interceptor

import com.star.wars.datasource.EndPoint.HEADER_CACHE_CONTROL
import com.star.wars.datasource.EndPoint.HEADER_PRAGMA
import com.star.wars.utility.ConnectionUtil
import okhttp3.CacheControl
import okhttp3.Interceptor
import okhttp3.Response
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class OfflineInterceptor @Inject constructor(
    private val connectionUtil: ConnectionUtil
) : Interceptor {
    private val isNetworkAvailable get() = connectionUtil.isNetworkInterfaceAvailable()
    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        if (!isNetworkAvailable) {
            val cacheControl = CacheControl.Builder().maxStale(7, TimeUnit.DAYS).build()
            request = request.newBuilder().addHeader("Content-Type", "application/json")
                .removeHeader(HEADER_PRAGMA).removeHeader(HEADER_CACHE_CONTROL)
                .cacheControl(cacheControl).build()
        }
        return chain.proceed(request)
    }

}