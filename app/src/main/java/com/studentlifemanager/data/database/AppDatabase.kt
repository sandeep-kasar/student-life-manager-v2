package com.studentlifemanager.data.database

import android.content.Context
import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.studentlifemanager.data.dao.ExpenseDao
import com.studentlifemanager.data.entity.ExpenseEntity

@Database(entities = [ExpenseEntity::class], version = 2, exportSchema = true, autoMigrations = [
    AutoMigration (from = 1, to = 2)
])
public abstract class AppRoomDatabase : RoomDatabase() {

    abstract fun myExpenseDao(): ExpenseDao

    companion object {
        // Singleton prevents multiple instances of database opening
        // at the same time.
        @Volatile
        private var INSTANCE: AppRoomDatabase? = null

        fun getDatabase(context: Context): AppRoomDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppRoomDatabase::class.java,
                    "app_database"
                ).build()
                INSTANCE = instance
                // return instance
                instance
            }
        }



    }
}