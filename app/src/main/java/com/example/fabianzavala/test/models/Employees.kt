/********************************************************************
 ** -----------------------------------------------------------------------------------------------
 ** Description: Class based on API REST results.
 **
 ** Change Log:
 ** Version      Date                Programmer               Description
 ** ----------      ---------------      ------------------------      ----------------------------
 ** 1.0             2021-02-25     Fabian ZV                   Created
 ** -----------------------------------------------------------------------------------------------
 ********************************************************************/

package com.example.fabianzavala.test.models

import java.io.Serializable

data class Employees (

    val employees:  List<Employee>

    )

data class Employee (

    val id: Int,
    val firstName: String,
    val lastName: String,
    val image: String,
    val description: String,
    val rating: Float
): Serializable