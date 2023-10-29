package com.studentlifemanager.database.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.studentlifemanager.database.data.entity.ExpenseEntity

/**
 * Dao interface for my expense operation
 *
 * @author SandeepK
 * @version 2.0
 * */

@Dao
interface ExpenseDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMyExpense(expenseEntity: ExpenseEntity)

    @Query(
        "select sum(ex_amount) As amount from my_expense where ex_type = (:cashType) and ex_month = (:month)"
    )
    fun getIncomeOrExpenseTotalByMonth(month: Int, cashType: Int): Long

    @Query(
        "SELECT DISTINCT ex_date FROM my_expense WHERE ex_month = (:month) ORDER BY ex_date DESC"
    )
    fun getAllDatesInMonth(month: Int): List<String>

    @Query(
        "select sum(ex_amount) As amount from my_expense where ex_date= (:date) and ex_type IN (1, 2)"
    )
    fun getExpenseTotalForDate(date: String): Long

    @Query(
        "SELECT * FROM my_expense WHERE ex_date = (:date) ORDER BY ex_id DESC"
    )
    fun getExpensesByDate(date: String): List<ExpenseEntity>

    @Query(
        "Delete from my_expense WHERE ex_id = (:exId)"
    )
    fun deleteExpenseById(exId: Int)

    @Query(
        "UPDATE my_expense SET ex_amount = (:amount), ex_title =(:title),ex_note =(:note),ex_date =(:date) WHERE ex_id = (:exId)"
    )
    fun updateExpenseById(amount: Long, title: String, note: String, date: String, exId: Int)

}
