/********************************************************************
 ** -----------------------------------------------------------------------------------------------
 ** Description: Class to add functionality to the employee list
 **
 ** Change Log:
 ** Version      Date                Programmer               Description
 ** ----------      ---------------      ------------------------      ----------------------------
 ** 1.0             2021-02-25     Fabian ZV                   Created
 ** -----------------------------------------------------------------------------------------------
 ********************************************************************/


package com.example.fabianzavala.test.controllers

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.preference.PreferenceManager
import com.example.fabianzavala.test.R
import com.example.fabianzavala.test.adapters.EmployeesAdapter
import com.example.fabianzavala.test.database.EmployeesDatabase
import com.example.fabianzavala.test.databinding.ActivityEmployeesBinding
import com.example.fabianzavala.test.models.Employee
import com.example.fabianzavala.test.models.Employees
import com.example.fabianzavala.test.repositories.EmployeesRepository
import com.example.fabianzavala.test.viewModels.EmployeesVMFactory
import com.example.fabianzavala.test.viewModels.EmployeesViewModel
import com.example.fabianzavala.test.webservice.APIEmployee
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EmployeesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEmployeesBinding
    private lateinit var employeesVM: EmployeesViewModel

    private val key = "MY_KEY"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEmployeesBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        supportActionBar?.title = getString(R.string.title_employeesActivity)

        val employeesDao  = EmployeesDatabase.getInstance(this).employeesDAO
        val employeesRepo = EmployeesRepository(employeesDao)
        val employeesFactory = EmployeesVMFactory(employeesRepo)
        employeesVM = ViewModelProvider(this, employeesFactory).get(EmployeesViewModel::class.java)

        binding.loading.visibility = View.VISIBLE
        getAllEmployees()

        binding.refreshEmployees.setOnRefreshListener {
            getAllEmployees()
        }

    }

    private fun getAllEmployees() {
        binding.refreshEmployees.isRefreshing = true

        APIEmployee.employeService.getEmployees().enqueue(object : Callback<Employees> {
            override fun onResponse(call: Call<Employees>, response: Response<Employees>) {

                binding.loading.visibility = View.GONE
                binding.refreshEmployees.isRefreshing = false

                if (response.isSuccessful) {
                    binding.employeesRv.adapter = EmployeesAdapter(response.body()!!.employees)
                    employeesVM.insertEmployees(response.body()!!.employees)
                }
            }

            override fun onFailure(call: Call<Employees>, t: Throwable) {
                binding.loading.visibility = View.GONE
                binding.refreshEmployees.isRefreshing = false
                getEmployeesData()
                Toast.makeText(
                    applicationContext, getString(R.string.networkError),
                    Toast.LENGTH_SHORT
                ).show()
            }

        })
    }

    private fun getEmployeesData() {
        employeesVM.employees.observe(this, Observer {

            var employeesAdapter: MutableList<Employee> = mutableListOf()

            for (employee in it) {
                val employeeAdapter = Employee(
                    id = employee.id,
                    firstName = employee.firstName,
                    lastName = employee.lastName,
                    image = employee.image,
                    description = employee.description,
                    rating = employee.rating
                )

                employeesAdapter.add(employeeAdapter)
            }

            binding.employeesRv.adapter = EmployeesAdapter(employeesAdapter)

        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_signout -> {

                val builder = AlertDialog.Builder(this)
                builder.setMessage(getString(R.string.signoutMessage))
                    .setTitle(getString(R.string.warning))
                builder.apply {
                    setPositiveButton(getString(R.string.signout),
                        DialogInterface.OnClickListener { dialogInterface, i ->

                            val preferences = PreferenceManager.getDefaultSharedPreferences(context)
                            val editor = preferences.edit()
                            editor.remove(key)
                            editor.apply()

                            val intentSingout = Intent(context, LoginActivity::class.java)
                            startActivity(intentSingout)
                            finish()
                        })
                    setNegativeButton(getString(R.string.cancel),
                        DialogInterface.OnClickListener { dialogInterface, i ->
                            dialogInterface.dismiss()
                        })
                }

                builder.create()
                builder.show()

            }
        }

        return super.onOptionsItemSelected(item)
    }
}