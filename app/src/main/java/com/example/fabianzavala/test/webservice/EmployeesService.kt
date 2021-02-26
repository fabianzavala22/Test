package com.example.fabianzavala.test.webservice

import com.example.fabianzavala.test.models.Employees
import retrofit2.Call
import retrofit2.http.GET

interface EmployeesService {

    @GET("/")
    fun getEmployees(): Call<Employees>
}