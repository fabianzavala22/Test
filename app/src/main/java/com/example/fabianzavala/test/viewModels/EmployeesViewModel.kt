/********************************************************************
 ** -----------------------------------------------------------------------------------------------
 ** Description: Class to add functionality EmployeesViewModel
 **
 ** Change Log:
 ** Version      Date                Programmer               Description
 ** ----------      ---------------      ------------------------      ----------------------------
 ** 1.0             2021-02-25     Fabian ZV                   Created
 ** -----------------------------------------------------------------------------------------------
 ********************************************************************/

package com.example.fabianzavala.test.viewModels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fabianzavala.test.database.EmployeeRoom
import com.example.fabianzavala.test.models.Employee
import com.example.fabianzavala.test.repositories.EmployeesRepository
import kotlinx.coroutines.launch

class EmployeesViewModel (private val repository: EmployeesRepository): ViewModel() {

    val employees : LiveData<List<EmployeeRoom>> = repository.employees

    fun insertEmployees(employees: List<Employee>) {
        repository.insertEmployees(employees)
    }

    fun updateEmployees(employees: List<EmployeeRoom>) = viewModelScope.launch{
        val newRowId = repository.updateEmployees(employees)
        if (newRowId > -1)
        {
            Log.e("User Room","UPDATED OK")
        } else
        {
            Log.e("User Room","Error Ocurred")
        }
    }
}