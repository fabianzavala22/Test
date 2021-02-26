/********************************************************************
 ** -----------------------------------------------------------------------------------------------
 ** Description: Class to define the states of the login form
 **
 ** Change Log:
 ** Version      Date                Programmer               Description
 ** ----------      ---------------      ------------------------      ----------------------------
 ** 1.0             2021-02-25     Fabian ZV                   Created
 ** -----------------------------------------------------------------------------------------------
 ********************************************************************/

package com.example.fabianzavala.test.classes

/*
* Data validation state of the login form.
*/

data class LoginFormState (

    val usernameError: Int?  = null,
    val passwordError: Int?  = null,
    val isDataValid: Boolean = false
)