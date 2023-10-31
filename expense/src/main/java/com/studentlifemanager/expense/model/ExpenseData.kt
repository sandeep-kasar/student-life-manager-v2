package com.studentlifemanager.expense.model

import com.studentlifemanager.common.data.entity.ExpenseEntity
import com.studentlifemanager.expense.ui.EXP_HEADER

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
