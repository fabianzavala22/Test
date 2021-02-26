/********************************************************************
** -----------------------------------------------------------------------------------------------
** Description: Class to create EmployeesViewModel
**
** Change Log:
** Version      Date                Programmer               Description
** ----------      ---------------      ------------------------      ----------------------------
** 1.0             2021-02-25     Fabian ZV                   Created
** -----------------------------------------------------------------------------------------------
********************************************************************/

package com.example.fabianzavala.test.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.fabianzavala.test.repositories.EmployeesRepository
import java.lang.IllegalArgumentException

class EmployeesVMFactory (private val repository: EmployeesRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(EmployeesViewModel::class.java)){
            return EmployeesViewModel(repository) as T
        }

        throw IllegalArgumentException("Unknonw View Model class")
    }
}