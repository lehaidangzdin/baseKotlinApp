package com.lhd.androidbase.data.database.daos

import androidx.room.Dao
import androidx.room.Query
import com.lhd.androidbase.data.database.entities.CustomerEntity

@Dao
interface CustomerDao {

    @Query("select * from customer")
    fun getAll(): List<CustomerEntity>

}