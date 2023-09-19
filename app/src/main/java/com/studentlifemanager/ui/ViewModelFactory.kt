package com.studentlifemanager.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.studentlifemanager.data.database.AppRoomDatabase
import com.studentlifemanager.ui.expense.ExpenseRepository
import com.studentlifemanager.ui.expense.ExpenseViewModel

/**
 * This is the factory class where we can return different viewModel
 * as per need
 *
 *
 * @author SandeepK
 * @version 2.0
 *
 * */
class ViewModelFactory(private val appRoomDatabase: AppRoomDatabase) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ExpenseViewModel::class.java)) {
            return ExpenseViewModel(ExpenseRepository(appRoomDatabase)) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }

}

