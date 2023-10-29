package com.studentlifemanager.database.data.module

import com.studentlifemanager.database.data.dao.ExpenseDao
import com.studentlifemanager.database.data.dao.PinDao
import com.studentlifemanager.database.data.database.AppDatabase
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

    @Provides
    fun providePinDao(
        appDatabase: AppDatabase
    ): PinDao = appDatabase.myPinDao()
}