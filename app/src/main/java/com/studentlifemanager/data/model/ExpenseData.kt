package com.studentlifemanager.data.model

import com.studentlifemanager.database.data.entity.ExpenseEntity
import com.studentlifemanager.ui.expense.EXP_HEADER

data class ExpenseDate(
    val date: String? = null,
    val total: Long = 0
)

data class ExpenseHeader(
    val income: Long = 0,
    val expense: Long = 0,
    val balance: Long = 0
)

data class ExpenseData(
    val expenseDate: ExpenseDate? = null,
    val expenseHeader: ExpenseHeader? = null,
    val expenseEntity: ExpenseEntity? = null,
    val layType: Int = EXP_HEADER
)
