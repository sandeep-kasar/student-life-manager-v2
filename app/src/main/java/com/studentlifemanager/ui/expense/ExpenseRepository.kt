package com.studentlifemanager.ui.expense

import androidx.annotation.WorkerThread
import com.studentlifemanager.data.database.AppRoomDatabase
import com.studentlifemanager.data.entity.ExpenseEntity
import kotlinx.coroutines.flow.Flow

/**
 * This is the repository class, whatever data needed in the app
 * is called from here only, Like insert data, get data etc.
 *
 * @param appDatabase : This is the room database instance
 *
 *
 * @author SandeepK
 * @version 2.0
 *
 * */
class ExpenseRepository(private val appDatabase: AppRoomDatabase) {

    // By default Room runs suspend queries off the main thread, therefore, we don't need to
    // implement anything else to ensure we're not doing long running database work
    // off the main thread.
    @WorkerThread
    suspend fun insert(expenseEntity: ExpenseEntity) {
        appDatabase.myExpenseDao().insertMyExpense(expenseEntity)
    }

    @WorkerThread
    suspend fun getIncomeOrExpenseTotalByMonth(month: Int, cashType: Int): Long {
        return appDatabase.myExpenseDao().getIncomeOrExpenseTotalByMonth(month, cashType)
    }

    @WorkerThread
    suspend fun getAllDatesInMonth(month: Int): List<String> {
        return appDatabase.myExpenseDao().getAllDatesInMonth(month)
    }

    @WorkerThread
    suspend fun getExpenseTotalForDate(date: String): Long {
        return appDatabase.myExpenseDao().getExpenseTotalForDate(date)
    }

    @WorkerThread
    suspend fun getExpensesByDate(date: String): List<ExpenseEntity> {
        return appDatabase.myExpenseDao().getExpensesByDate(date)
    }

    @WorkerThread
    suspend fun deleteExpenseById(exId: Int) {
        appDatabase.myExpenseDao().deleteExpenseById(exId)
    }

    @WorkerThread
    suspend fun updateExpenseById(
        amount: Long, title: String, note: String, date: String, exId: Int
    ) {
        appDatabase.myExpenseDao().updateExpenseById(amount, title, note, date, exId)
    }
}