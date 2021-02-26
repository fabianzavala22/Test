/********************************************************************
 ** -----------------------------------------------------------------------------------------------
 ** Description: Class to implement the methods to manage employees data from Room
 **
 ** Change Log:
 ** Version      Date                Programmer               Description
 ** ----------      ---------------      ------------------------      ----------------------------
 ** 1.0             2021-02-25     Fabian ZV                   Created
 ** -----------------------------------------------------------------------------------------------
 ********************************************************************/

package com.example.fabianzavala.test.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface EmployeesDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(employees: List<EmployeeRoom>)

    @Update
    suspend fun updateAll(employees: List<EmployeeRoom>) : Int

    @Query("SELECT * FROM employees_table WHERE employee_id = :key")
    fun getEmployee(key: Int): LiveData<EmployeeRoom>

    @Query("SELECT * FROM employees_table")
    fun getAll(): LiveData<List<EmployeeRoom>>
}