package com.studentlifemanager.common.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.studentlifemanager.common.data.entity.PinEntity
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

    @Query(value = "SELECT * FROM pin ORDER BY pn_id DESC")
    fun getAllPins(): Flow<List<PinEntity>>
}
