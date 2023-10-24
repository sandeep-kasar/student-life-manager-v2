package com.studentlifemanager.pin.screen

import androidx.annotation.WorkerThread
import com.studentlifemanager.database.data.dao.ExpenseDao
import com.studentlifemanager.database.data.dao.PinDao
import com.studentlifemanager.database.data.entity.ExpenseEntity
import com.studentlifemanager.database.data.entity.PinEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * This is the repository class, whatever data needed in the app
 * is called from here only, Like insert data, get data etc.
 *
 * @param pinDao : This is the PinDao instance
 *
 *
 * @author SandeepK
 * @version 2.0
 *
 * */
class PinRepository @Inject constructor(private val pinDao: PinDao) {

    // By default Room runs suspend queries off the main thread, therefore, we don't need to
    // implement anything else to ensure we're not doing long running database work
    // off the main thread.
    @WorkerThread
    suspend fun insert(pinEntity: PinEntity) {
        pinDao.insertPin(pinEntity)
    }

    @WorkerThread
    suspend fun getAllPins(): Flow<List<PinEntity>> {
        return pinDao.getAllPins()
    }


}