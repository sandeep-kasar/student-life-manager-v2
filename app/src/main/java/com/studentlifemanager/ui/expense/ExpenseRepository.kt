package com.studentlifemanager.ui.expense

import androidx.annotation.WorkerThread
import com.studentlifemanager.database.data.dao.ExpenseDao
import com.studentlifemanager.database.data.entity.ExpenseEntity
import javax.inject.Inject

/**
 * This is the repository class, whatever data needed in the app
 * is called from here only, Like insert data, get data etc.
 *
 * @param expenseDao : This is the ExpenseDao instance
 *
 *
 * @author SandeepK
 * @version 2.0
 *
 * */
class ExpenseRepository @Inject constructor(private val expenseDao: ExpenseDao) {

    // By default Room runs suspend queries off the main thread, therefore, we don't need to
    // implement anything else to ensure we're not doing long running database work
    // off the main thread.
    @WorkerThread
    suspend fun insert(expenseEntity: ExpenseEntity) {
        expenseDao.insertMyExpense(expenseEntity)
    }

    @WorkerThread
    suspend fun getIncomeOrExpenseTotalByMonth(month: Int, cashType: Int): Long {
        return expenseDao.getIncomeOrExpenseTotalByMonth(month, cashType)
    }

    @WorkerThread
    suspend fun getAllDatesInMonth(month: Int): List<String> {
        return expenseDao.getAllDatesInMonth(month)
    }

    @WorkerThread
    suspend fun getExpenseTotalForDate(date: String): Long {
        return expenseDao.getExpenseTotalForDate(date)
    }

    @WorkerThread
    suspend fun getExpensesByDate(date: String): List<ExpenseEntity> {
        return expenseDao.getExpensesByDate(date)
    }

    @WorkerThread
    suspend fun deleteExpenseById(exId: Int) {
        expenseDao.deleteExpenseById(exId)
    }

    @WorkerThread
    suspend fun updateExpenseById(
        amount: Long, title: String, note: String, date: String, exId: Int
    ) {
        expenseDao.updateExpenseById(amount, title, note, date, exId)
    }
}