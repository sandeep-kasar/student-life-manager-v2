package com.studentlifemanager.common.data.module

import com.studentlifemanager.common.data.dao.ExpenseDao
import com.studentlifemanager.common.data.dao.PinDao
import com.studentlifemanager.common.data.database.AppDatabase
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