package com.studentlifemanager.database.data.database

import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.RoomDatabase
import com.studentlifemanager.database.data.dao.ExpenseDao
import com.studentlifemanager.database.data.dao.PinDao
import com.studentlifemanager.database.data.entity.ExpenseEntity
import com.studentlifemanager.database.data.entity.PinEntity

@Database(
    entities = [
        ExpenseEntity::class,
        PinEntity::class
    ],
    version = 3,
    autoMigrations = [
        AutoMigration(from = 1, to = 2),
        AutoMigration(from = 2, to = 3)
    ],
    exportSchema = true,
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun myExpenseDao(): ExpenseDao
    abstract fun myPinDao(): PinDao
}