package com.studentlifemanager.data.module

import com.studentlifemanager.data.dao.ExpenseDao
import com.studentlifemanager.data.database.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
class DaoModule {

    @Provides
    fun provideExpenseDao(
        appDatabase: AppDatabase
    ): ExpenseDao = appDatabase.myExpenseDao()
}