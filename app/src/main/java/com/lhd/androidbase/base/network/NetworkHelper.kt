package com.lhd.androidbase.base.network

import com.lhd.androidbase.BuildConfig
import com.lhd.androidbase.common.DataLocal

object NetworkHelper {

    fun getDefaultHeader(): Map<String, String> {
        val headers = mutableMapOf<String, String>()
        headers["Content-Type"] = "application/json"
        headers["Authorization"] = "Bearer ${BuildConfig.ACCESS_TOKEN}"
        return headers.toMap()
    }

    fun getDefaultHeaderForCustomer(): Map<String, String> {
        val headers = mutableMapOf<String, String>()
        headers["Content-Type"] = "application/json"
        headers["Authorization"] = "Bearer ${DataLocal.CUSTOMER_TOKEN}"
        return headers.toMap()
    }

}