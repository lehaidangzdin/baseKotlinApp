package com.lhd.android.data.repositories

import com.lhd.android.data.services.CustomerLocalService
import com.lhd.android.data.services.CustomerRemoteService
import com.lhd.android.di.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher

class CustomerRepository constructor(
    private val customerRemoteService: CustomerRemoteService,
    private val customerLocalService: CustomerLocalService,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) {
}