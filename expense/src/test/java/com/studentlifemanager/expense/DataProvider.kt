package com.studentlifemanager.expense

import com.studentlifemanager.common.data.entity.ExpenseEntity

object DataProvider {
    val expenseEntity1 = ExpenseEntity(
        exId = 1,
        exAmount = 100,
        exTitle = "Sh0pping",
        exNote = "Vedu school shopping",
        exType = 1,
        exDate = "12 jan 2024",
        exMonth = 1
    )
    val expenseEntity2 = ExpenseEntity(
        exId = 2,
        exAmount = 1000,
        exTitle = "Health",
        exNote = "Vedu school shopping",
        exType = 2,
        exDate = "13 jan 2024",
        exMonth = 1
    )

    val expenseEntityList = arrayListOf(
        expenseEntity1, expenseEntity2
    )
}