package com.biodun.roomassignment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.biodun.roomassignment.databinding.ActivitySignUpBinding
import com.biodun.roomassignment.db.LoginRepository
import com.biodun.roomassignment.db.entities.LoginEntity

class SignUpActivity : AppCompatActivity() {


    private lateinit var binding: ActivitySignUpBinding
    private lateinit var loginRepository: LoginRepository


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.alreadySignedInTv.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
        dataSetUp(binding)
    }


    private fun dataSetUp(binding: ActivitySignUpBinding) {
        loginRepository = LoginRepository(this)

        val firstNameEditText = binding.firstNameEditText
        val lastNameEditText = binding.lastNameEditText
        val phoneEditText = binding.phoneEditText
        val emailEditText = binding.emailEditText
        val passwordEditText = binding.passwordEditText

        binding.signUpButton.setOnClickListener {

            if(emailEditText.text.toString().isEmpty()) {
                Toast.makeText(this, "All fields are required", Toast.LENGTH_SHORT).show()
                return@setOnClickListener //terminate onClick instruction
            }

            loginRepository.getUserByEmail(emailEditText.text.toString()).observe(this, Observer {
                Log.e("IUKCBIAUIOVPOVPVO{", it.toString())
                if (it != null) {
                    Toast.makeText(this, "Email already in use", Toast.LENGTH_SHORT).show()
                    return@Observer
                }

                val successful = insertDataIntoDb(
                    LoginEntity(
                        firstName = firstNameEditText.text.toString(),
                        lastName = lastNameEditText.text.toString(),
                        phone = phoneEditText.text.toString(),
                        email = emailEditText.text.toString(),
                        password = passwordEditText.text.toString()
                    )
                )

                val intent = Intent(this, LoginActivity::class.java)
                if (successful) {
                    startActivity(intent)
                }
            })
        }

    }

    private fun insertDataIntoDb(loginEntity: LoginEntity): Boolean {
//        Log.e("IUKCBIAUIOVPOVPVO{", loginEntity.toString())
        if (loginEntity.firstName.isEmpty() || loginEntity.lastName.isEmpty() ||
            loginEntity.phone.isEmpty() || loginEntity.password.isEmpty()
        ) {
            Toast.makeText(this, "All fields are required", Toast.LENGTH_SHORT).show()
            return false
        }

        loginRepository.saveDetails(loginEntity)
        return true
    }

//    fun signUpBtnFunc(view: View) {}

}
