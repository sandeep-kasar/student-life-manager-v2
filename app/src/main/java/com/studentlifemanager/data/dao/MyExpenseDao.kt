package com.studentlifemanager.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.studentlifemanager.data.entity.MyExpenseEntity
import kotlinx.coroutines.flow.Flow

/**
 * Dao interface for my expense operation
 *
 * @author SandeepK
 * @version 2.0
 * */

@Dao
interface MyExpenseDao {

    @Insert
    fun insertMyExpense(myExpenseEntity: MyExpenseEntity)

    @Query(
        "select sum(ex_amount) As amount from my_expense where ex_type = 0 and month =(:month)"
    )
    fun getExpenses(month:String) : Flow<List<MyExpenseEntity>>

}