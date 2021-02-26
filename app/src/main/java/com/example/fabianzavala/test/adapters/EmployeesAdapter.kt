/********************************************************************
 ** -----------------------------------------------------------------------------------------------
 ** Description: Class to define the employee adapter
 **
 ** Change Log:
 ** Version      Date                Programmer               Description
 ** ----------      ---------------      ------------------------      ----------------------------
 ** 1.0             2021-02-25     Fabian ZV                   Created
 ** -----------------------------------------------------------------------------------------------
 ********************************************************************/

package com.example.fabianzavala.test.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.fabianzavala.test.controllers.DetailActivity
import com.example.fabianzavala.test.R
import com.example.fabianzavala.test.models.Employee

class EmployeesAdapter(private val employees: List<Employee>): RecyclerView.Adapter<EmployeesAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.employees_adapter, parent,false)

        return ViewHolder(view)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val employee = employees[position]
        holder.bind(employee)

        holder.itemView.setOnClickListener {

            val intentDetail = Intent(holder.itemView.context, DetailActivity::class.java)
            intentDetail.putExtra("itemDetail",employee)
            holder.itemView.context.startActivity(intentDetail)
        }
    }

    override fun getItemCount(): Int = employees.size

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {

        private val photo = view.findViewById<ImageView>(R.id.employee_imv)
        private val name  = view.findViewById<TextView>(R.id.employeName_tv)
        private val lastName  = view.findViewById<TextView>(R.id.employeLastName_tv)
        private val rating = view.findViewById<RatingBar>(R.id.employeRating_tv)

        fun bind(employee: Employee) {

            name.text     =  employee.firstName
            lastName.text =  employee.lastName
            rating.rating =  employee.rating

            Glide.with(photo.context).load(employee.image).into(photo)

        }
    }

}