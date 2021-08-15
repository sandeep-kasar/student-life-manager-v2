package com.studentlifemanager.ui.expense

import androidx.annotation.WorkerThread
import androidx.room.RoomDatabase
import com.studentlifemanager.data.database.AppRoomDatabase
import com.studentlifemanager.data.entity.MyExpenseEntity
import kotlinx.coroutines.flow.Flow
import java.time.Month

// Declares the DAO as a private property in the constructor. Pass in the DAO
// instead of the whole database, because you only need access to the DAO
class MyExpenseRepository(private val appDatabase: AppRoomDatabase) {

    // By default Room runs suspend queries off the main thread, therefore, we don't need to
    // implement anything else to ensure we're not doing long running database work
    // off the main thread.
    @WorkerThread
    suspend fun insert(myExpenseEntity: MyExpenseEntity) {
        appDatabase.myExpenseDao().insertMyExpense(myExpenseEntity)
    }

    @WorkerThread
    suspend fun getExpenses(month: String): Flow<List<MyExpenseEntity>>{
        return appDatabase.myExpenseDao().getExpenses(month)
    }
}