package com.studentlifemanager.database.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.studentlifemanager.database.data.entity.PinEntity
import kotlinx.coroutines.flow.Flow

/**
 * Dao interface for pin operation
 *
 * @author SandeepK
 * @version 2.0
 * */

@Dao
interface PinDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPin(pinEntity: PinEntity)

    @Query(value = "SELECT * FROM pin")
    fun getAllPins(): Flow<List<PinEntity>>
}
