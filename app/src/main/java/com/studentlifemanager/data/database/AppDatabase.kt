package com.studentlifemanager.data.database

import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.RoomDatabase
import com.studentlifemanager.data.dao.ExpenseDao
import com.studentlifemanager.data.entity.ExpenseEntity

@Database(
    entities = [
        ExpenseEntity::class
    ],
    version = 2,
    autoMigrations = [
        AutoMigration(from = 1, to = 2)
    ],
    exportSchema = true,
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun myExpenseDao(): ExpenseDao
}