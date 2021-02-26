/********************************************************************
** -----------------------------------------------------------------------------------------------
** Description: Class to implement the methods to get Employees data from Room
**
** Change Log:
** Version      Date                Programmer               Description
** ----------      ---------------      ------------------------      ----------------------------
** 1.0             2021-02-25     Fabian ZV                   Created
** -----------------------------------------------------------------------------------------------
********************************************************************/

package com.example.fabianzavala.test.repositories

import androidx.lifecycle.LiveData
import com.example.fabianzavala.test.database.EmployeeRoom
import com.example.fabianzavala.test.database.EmployeesDAO
import com.example.fabianzavala.test.models.Employee
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class EmployeesRepository (private val dao: EmployeesDAO) {

    val employees: LiveData<List<EmployeeRoom>> = dao.getAll()
    private val employeeScope = CoroutineScope(Job() + Dispatchers.IO)

    fun insertEmployees(employees: List<Employee>) {

        var employeesRoom: MutableList<EmployeeRoom> = mutableListOf()

        for (employee in employees) {
            val employeeRoom = EmployeeRoom(
                id = employee.id,
                firstName = employee.firstName,
                lastName = employee.lastName,
                image = employee.image,
                description = employee.description,
                rating = employee.rating
            )
            employeesRoom.add(employeeRoom)
        }

        employeeScope.launch {
            dao.insertAll(employeesRoom)
            /*val newRowId =
            if (newRowId > -1)
            {
                Log.e("ROOM INSERTED User:", employeesRoom.toString())
            } else
            {
                Log.e("ROOM FAILED User:", employeesRoom.toString())
            }*/
        }
    }

    suspend fun updateEmployees(employees: List<EmployeeRoom>): Int {
        return dao.updateAll(employees)
    }
}