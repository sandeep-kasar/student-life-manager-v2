package com.studentlifemanager.ui.expense

import com.studentlifemanager.database.data.entity.ExpenseEntity

/**
 * This is the interface for Expense Fragment
 * This interface has methods to handle insert, update and delete action
 *
 *
 * @author SandeepK
 * @version 2.0
 * */
interface IExpenseOperation {
    fun saveOrUpdateData(expenseEntity: ExpenseEntity, isToUpdate: Boolean = false)
    fun deleteData(expenseEntity: ExpenseEntity)
}