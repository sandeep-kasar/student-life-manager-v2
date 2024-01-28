package com.studentlifemanager.expense.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.studentlifemanager.common.data.entity.ExpenseEntity
import com.studentlifemanager.common.utils.Constant
import com.studentlifemanager.expense.model.ExpenseData
import com.studentlifemanager.expense.model.ExpenseDate
import com.studentlifemanager.expense.model.ExpenseHeader
import com.studentlifemanager.expense.module.IoDispatcher
import com.studentlifemanager.expense.repository.ExpenseRepository
import com.studentlifemanager.expense.ui.EXP_BODY
import com.studentlifemanager.expense.ui.EXP_DATE
import com.studentlifemanager.expense.ui.EXP_HEADER
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * This is the viewModel for Expense Fragment
 * Here we are managing the expense view related data
 *
 *
 * @author SandeepK
 * @version 2.0
 *
 * */

@HiltViewModel
class ExpenseViewModel @Inject constructor(
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    private val repository: ExpenseRepository
) :
    ViewModel() {

    private val _selectedMonth = MutableLiveData<Int>()

    val selectedMonth: LiveData<Int> get() = _selectedMonth

    private val expenseDataList = ArrayList<ExpenseData>()

    val expenseData = MutableLiveData<ArrayList<ExpenseData>>()

    /**
     * Launching a new coroutine to insert the data in a non-blocking way
     * used to insert the user expenses in my-expense table
     *
     * @param expenseEntity : this is the model class object which
     * has all information
     */
    fun insert(expenseEntity: ExpenseEntity) {
        viewModelScope.launch(ioDispatcher) {
            val isInserted = async { repository.insert(expenseEntity) }
            isInserted.await()
            if (isInserted.isCompleted) {
                getExpenseData(expenseEntity.exMonth)
            }
        }
    }

    /**
     * Launching a new coroutine to update the data in a non-blocking way
     * used to update the user expenses in my-expense table
     *
     * @param entity : this is the model class object which
     * has all information
     */
    fun updateExpenseById(entity: ExpenseEntity) {
        viewModelScope.launch(ioDispatcher) {
            val isUpdated = async {
                repository.updateExpenseById(
                    entity.exAmount, entity.exTitle, entity.exNote, entity.exDate, entity.exId
                )
            }
            isUpdated.await()
            if (isUpdated.isCompleted) {
                getExpenseData(entity.exMonth)
            }
        }
    }

    /**
     * Launching a new coroutine to delete the data in a non-blocking way
     * used to delete the user expenses in my-expense table
     *
     * @param expenseEntity : this is the model class object which
     * has all information
     */
    fun deleteExpenseById(expenseEntity: ExpenseEntity) {
        viewModelScope.launch(ioDispatcher) {
            val isDeleted = async { repository.deleteExpenseById(expenseEntity.exId) }
            isDeleted.await()
            if (isDeleted.isCompleted) {
                getExpenseData(expenseEntity.exMonth)
            }
        }
    }

    /**
     * To get income  and expense data, which will in be the list
     * of ExpenseEntity model
     *
     * @param month : expense month
     */
    fun getExpenseData(month: Int) {

        // clear old data in list
        expenseDataList.clear()

        // get header data, income, expense and total
        // get dates and body
        viewModelScope.launch(ioDispatcher) {

            // get header data
            val income = repository.getIncomeOrExpenseTotalByMonth(month, Constant.INCOME)
            val expense = repository.getIncomeOrExpenseTotalByMonth(month, Constant.EXPENSE)
            var balance = income - expense
            expenseDataList.add(
                ExpenseData(
                    expenseHeader = ExpenseHeader(
                        income = income, expense = expense, balance = balance
                    ), layType = EXP_HEADER
                )
            )

            // get expenses by date
            val arrayListDate = repository.getAllDatesInMonth(month)
            for (date in arrayListDate) {
                val expenseTotalForDate = repository.getExpenseTotalForDate(date)
                expenseDataList.add(
                    ExpenseData(
                        expenseDate = ExpenseDate(date, expenseTotalForDate), layType = EXP_DATE
                    )
                )
                // get transaction data, body data
                val entityList = repository.getExpensesByDate(date)
                for (entity in entityList) {
                    expenseDataList.add(
                        ExpenseData(
                            expenseEntity = entity, layType = EXP_BODY
                        )
                    )
                }
            }
            expenseData.postValue(expenseDataList)
        }
    }

    /**
     *   on select of month, update selectedMonth
     *   this variable is used to communicate month value across different fragments
     *   Ex. ExpenseFragment
     */
    fun selectItem(month: Int) {
        _selectedMonth.value = month
    }
}