package com.lhd.androidbase.data.services

import com.lhd.androidbase.data.database.daos.CustomerDao
import javax.inject.Inject

class CustomerLocalService @Inject constructor(private val customerDao: CustomerDao) {
}