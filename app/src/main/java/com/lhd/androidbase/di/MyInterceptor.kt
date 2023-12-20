package com.lhd.androidbase.di

import android.util.Log
import com.lhd.androidbase.di.CommonInterceptor.Companion.TAG
import okhttp3.CacheControl
import okhttp3.Headers
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response


class CommonInterceptor {
    companion object {
        const val TAG = "Interceptor"
    }
}

class MyInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originRequest: Request = chain.request()
        //
        val headers = Headers.Builder()
            .add("Authorization", "auth_key")
            .add("User-Agent", "you-app-name")
            .build()

        val newRequest: Request = originRequest.newBuilder()
            .addHeader("Authorization", "auth-value") //thêm một header với name và value.
            .addHeader("User-Agent", "you-app-name")
            .cacheControl(CacheControl.FORCE_CACHE) //  Đặt kiểm soát header là của request này, replace lên mọi header đã có.
            .headers(headers) //Removes all headers on this builder and adds headers.
            .method(
                originRequest.method,
                originRequest.body
            ) // Adds request method and request body
//            .removeHeader("Authorization") // Removes all the headers with this name
            .build()

        return chain.proceed(newRequest)
    }


}

class RetryInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        //
        Log.e(TAG, "intercept: Retry interceptor")
        //retry rest api
        var request = chain.request()
        var response = chain.proceed(request)
        var retryCount = 0

        while (!response.isSuccessful && retryCount < 3) {
            retryCount++
            request = request.newBuilder().build()
            response.close()
            response = chain.proceed(request)
        }

        return response
    }

}