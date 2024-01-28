package com.studentlifemanager.expense.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.studentlifemanager.MainCoroutineRule
import com.studentlifemanager.common.utils.Constant
import com.studentlifemanager.expense.DataProvider.expenseEntity1
import com.studentlifemanager.expense.DataProvider.expenseEntityList
import com.studentlifemanager.expense.repository.ExpenseRepository
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test


class ExpenseViewModelTest {

    @OptIn(ExperimentalCoroutinesApi::class)
    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @MockK
    lateinit var expenseRepository: ExpenseRepository

    lateinit var expenseViewModel: ExpenseViewModel
    val dispatcher = Dispatchers.Unconfined

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        expenseViewModel = ExpenseViewModel(dispatcher, expenseRepository)
    }

    @Test
    fun test_insertExpense() = runBlocking {

        coEvery {
            expenseRepository.insert(expenseEntity1)
        } returns Unit

        coEvery {
            expenseRepository.getIncomeOrExpenseTotalByMonth(1, Constant.INCOME)
        } returns 1000

        coEvery {
            expenseRepository.getIncomeOrExpenseTotalByMonth(1, Constant.EXPENSE)
        } returns 100

        coEvery {
            expenseRepository.getAllDatesInMonth(1)
        } returns listOf(expenseEntity1.exDate)

        coEvery {
            expenseRepository.getExpenseTotalForDate(expenseEntity1.exDate)
        } returns 100

        coEvery {
            expenseRepository.getExpensesByDate(expenseEntity1.exDate)
        } returns expenseEntityList

        expenseViewModel.insert(expenseEntity1)
        expenseViewModel.expenseData.observeForever {}
        assert(expenseViewModel.expenseData.value != null)
        assert(expenseViewModel.expenseData.value?.size == 4)
    }
}