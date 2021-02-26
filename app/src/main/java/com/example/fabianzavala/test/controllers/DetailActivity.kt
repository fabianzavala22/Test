/********************************************************************
 ** -----------------------------------------------------------------------------------------------
 ** Description: Class to display employee details
 **
 ** Change Log:
 ** Version      Date                Programmer               Description
 ** ----------      ---------------      ------------------------      ----------------------------
 ** 1.0             2021-02-25     Fabian ZV                   Created
 ** -----------------------------------------------------------------------------------------------
 ********************************************************************/

package com.example.fabianzavala.test.controllers

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.fabianzavala.test.R
import com.example.fabianzavala.test.databinding.ActivityDetailBinding
import com.example.fabianzavala.test.models.Employee

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        binding = ActivityDetailBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        supportActionBar?.title = getString(R.string.title_DetailActivity)

        val itemDetail: Employee = intent.extras?.getSerializable("itemDetail") as Employee

        Glide.with(this).load(itemDetail.image).into(binding.employeePhotoImv)
        binding.employeNameTv.text        = itemDetail.firstName
        binding.employeLastNameTv.text    = itemDetail.lastName
        binding.employeDescriptionTv.text = itemDetail.description

    }
}