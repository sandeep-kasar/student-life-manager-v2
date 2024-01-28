package com.studentlifemanager.expense.repository

import com.studentlifemanager.common.data.dao.ExpenseDao
import com.studentlifemanager.expense.DataProvider.expenseEntity1
import com.studentlifemanager.expense.DataProvider.expenseEntity2
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class ExpenseRepositoryTest {

    @MockK
    lateinit var expenseDao: ExpenseDao

    lateinit var expenseRepository: ExpenseRepository

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        expenseRepository = ExpenseRepository(expenseDao)
    }

    @Test
    fun insert() = runTest {
        coEvery {
            expenseDao.insertMyExpense(expenseEntity1)
        } returns Unit
        expenseRepository.insert(expenseEntity1)
    }

    @Test
    fun getIncomeOrExpenseTotalByMonth() = runTest {
        coEvery {
            expenseDao.getIncomeOrExpenseTotalByMonth(1, 1)
        } returns 1000

        val result = expenseRepository.getIncomeOrExpenseTotalByMonth(1, 1)
        Assert.assertEquals(result, 1000)

    }

    @Test
    fun getAllDatesInMonth() = runTest {
        coEvery {
            expenseDao.getAllDatesInMonth(1)
        } returns arrayListOf(expenseEntity1.exDate, expenseEntity2.exDate)

        val allDatesList = expenseRepository.getAllDatesInMonth(1)

        Assert.assertEquals(allDatesList.size, 2)

    }
}