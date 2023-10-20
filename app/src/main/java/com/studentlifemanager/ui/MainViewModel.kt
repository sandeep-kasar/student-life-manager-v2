package com.studentlifemanager.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * This is the viewModel class for MainActivity
 *
 * @author SandeepK
 * @version 2.0
 */
@HiltViewModel
class MainViewModel @Inject constructor() : ViewModel() {

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