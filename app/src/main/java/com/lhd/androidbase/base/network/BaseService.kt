package com.lhd.androidbase.base.network


abstract class BaseService {

    protected fun parseError(
        responseMessage: String?,
        responseCode: Int,
        errorBody: String?
    ): BaseNetworkException {

        val baseNetworkException = BaseNetworkException(responseMessage, responseCode)
        errorBody?.let {
            baseNetworkException.parseFromString(responseMessage ?: it)
        }

        return baseNetworkException
    }

    protected fun parseNetworkErrorException(throwable: Throwable): NetworkErrorException {
        return NetworkErrorException(throwable.message)
    }


}