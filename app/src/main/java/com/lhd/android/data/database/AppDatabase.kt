package com.lhd.android.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.lhd.android.data.database.daos.CustomerDao
import com.lhd.android.data.database.entities.CustomerEntity

@Database(entities = [CustomerEntity::class],version = 1)
abstract class AppDatabase : RoomDatabase(){
    abstract fun customerDao(): CustomerDao
}