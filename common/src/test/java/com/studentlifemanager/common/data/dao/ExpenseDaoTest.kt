package com.studentlifemanager.common.data.dao

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import com.google.common.truth.Truth.assertThat
import com.studentlifemanager.common.data.database.AppDatabase
import com.studentlifemanager.common.data.entity.ExpenseEntity
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
@MediumTest
class ExpenseDaoTest {

    lateinit var appDatabase: AppDatabase
    private lateinit var expenseDao: ExpenseDao


    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        appDatabase = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            AppDatabase::class.java
        ).allowMainThreadQueries().build()

        expenseDao = appDatabase.myExpenseDao()

    }

    @After
    fun tearDown() {
        appDatabase.close()
    }

    @Test
    fun insertMyExpense() = runBlocking {
        val expenseEntity = ExpenseEntity(
            exId = 1,
            exAmount = 100,
            exTitle = "Shpping",
            exNote = "Vedu school shopping",
            exType = 1,
            exDate = "12 jan 2024",
            exMonth = 1
        )
        expenseDao.insertMyExpense(expenseEntity)

        val allExpenseItem = expenseDao.getExpensesByDate("12 jan 2024")
        assertThat(allExpenseItem).contains(allExpenseItem[0])
    }

    @Test
    fun deleteExpenseById() = runBlocking {
        val expenseEntity = ExpenseEntity(
            exId = 1,
            exAmount = 100,
            exTitle = "Shpping",
            exNote = "Vedu school shopping",
            exType = 1,
            exDate = "12 jan 2024",
            exMonth = 1
        )
        expenseDao.insertMyExpense(expenseEntity)
        expenseDao.deleteExpenseById(1)
        val allExpenseItem = expenseDao.getExpensesByDate("12 jan 2024")
        assertThat(allExpenseItem).doesNotContain(allExpenseItem)
    }

}