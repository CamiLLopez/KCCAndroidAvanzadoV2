package com.example.entregaandroidavanzadov2.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.widget.doAfterTextChanged
import com.example.entregaandroidavanzadov2.databinding.ActivityLoginBinding
import com.example.entregaandroidavanzadov2.ui.viewModels.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {


    private lateinit var binding: ActivityLoginBinding
    private val viewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.emailField.doAfterTextChanged {
            binding.loginButton.isEnabled = binding.emailField.text.isNotEmpty() && binding.passwordField.text.isNotEmpty()

        }

        binding.passwordField.doAfterTextChanged {
            binding.loginButton.isEnabled = binding.emailField.text.isNotEmpty() && binding.passwordField.text.isNotEmpty()

        }

        binding.loginButton.setOnClickListener {

            val email = binding.emailField.text.toString()
            val password = binding.passwordField.text.toString()

            viewModel.login(email, password)

            viewModel.loginResult.observe(this) { success ->

                if (!success){
                    Toast.makeText(this, "Login failed, try again", Toast.LENGTH_LONG).show()
                }else{
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                }
            }

        }
    }
}