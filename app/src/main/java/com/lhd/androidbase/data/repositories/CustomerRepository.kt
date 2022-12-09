package com.lhd.androidbase.data.repositories

import com.lhd.androidbase.data.services.CustomerLocalService
import com.lhd.androidbase.data.services.CustomerRemoteService
import com.lhd.androidbase.di.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher

class CustomerRepository constructor(
    private val customerRemoteService: CustomerRemoteService,
    private val customerLocalService: CustomerLocalService,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) {
}