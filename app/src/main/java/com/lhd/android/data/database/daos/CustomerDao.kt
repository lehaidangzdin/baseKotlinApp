package com.lhd.android.data.database.daos

import androidx.room.Dao
import androidx.room.Query
import com.lhd.android.data.database.entities.CustomerEntity

@Dao
interface CustomerDao {

    @Query("select * from customer")
    fun getAll(): List<CustomerEntity>

}