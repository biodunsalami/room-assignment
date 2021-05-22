package com.biodun.roomassignment

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.biodun.roomassignment.databinding.ActivityLoginBinding
import com.biodun.roomassignment.db.LoginRepository

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var loginRepository: LoginRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loginRepository = LoginRepository(this)

        binding.loginButton.setOnClickListener {
            logMeIn()
        }
    }

    private fun logMeIn() {
        val email = binding.emailLoginEditText
        val password = binding.passwordLoginEditText
//        Log.e("IUKCBIAUIOVPOVPVO{", "The input email is ${email.text.toString()}")

        loginRepository.getUserByEmail(email.text.toString()).observe(this, Observer {
//            Log.e("IUKCBIAUIOVPOVPVO{", it.toString())
            if (it == null) {
                Toast.makeText(this, "User not found, please sign-up", Toast.LENGTH_SHORT).show()
                return@Observer
            }
            val passwordMatch = password.text.toString() == it.password

            if (!passwordMatch) {
                Toast.makeText(this, "Incorrect password", Toast.LENGTH_SHORT).show()
                return@Observer
            }

            val intent = Intent(this, CategoryActivity::class.java)
            startActivity(intent)
        })

    }

}
