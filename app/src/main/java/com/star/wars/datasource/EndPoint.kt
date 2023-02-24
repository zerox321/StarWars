package com.star.wars.datasource

object EndPoint {
    const val HEADER_CACHE_CONTROL = "Cache-Control"
    const val HEADER_PRAGMA = "Pragma"
    const val CACHE_SIZE: Long = (10 * 1024 * 1024).toLong()// 10 MB

    const val timeOut = 60L
    const val pageSize = 20


    const val SendOtpCodeConstant = "Patient/SendOtpCode"

}
