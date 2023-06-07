package com.lhd.android.base.network

import com.lhd.android.BuildConfig
import com.lhd.android.common.DataLocal

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