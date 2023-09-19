package com.studentlifemanager.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * This is the viewModel class for MainActivity
 *
 * @author SandeepK
 * @version 2.0
 */
class MainViewModel : ViewModel() {

    private val _selectedMonth = MutableLiveData<Int>()

    val selectedMonth: LiveData<Int> get() = _selectedMonth

    /**
     *   on select of month, update selectedMonth
     *   this variable is used to communicate month value across different fragments
     *   Ex. ExpenseFragment
     */
    fun selectItem(month: Int) {
        _selectedMonth.value = month
    }


}