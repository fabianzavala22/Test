/********************************************************************
 ** -----------------------------------------------------------------------------------------------
 ** Description: Class to implements the Employees Database in Room
 **
 ** Change Log:
 ** Version      Date                Programmer               Description
 ** ----------      ---------------      ------------------------      ----------------------------
 ** 1.0             2021-02-25     Fabian ZV                   Created
 ** -----------------------------------------------------------------------------------------------
 ********************************************************************/


package com.example.fabianzavala.test.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "employees_table")
data class EmployeeRoom (

    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "employee_id")
    var id          : Int,

    @ColumnInfo(name = "firstName")
    var firstName   : String,

    @ColumnInfo(name = "lastName")
    var lastName    : String,

    @ColumnInfo(name = "image")
    var image    : String,

    @ColumnInfo(name = "description")
    var description : String,

    @ColumnInfo(name = "rating")
    var rating  : Float,
)