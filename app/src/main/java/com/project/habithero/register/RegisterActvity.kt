package com.project.habithero.register

import android.content.Intent
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.view.MenuItem
import android.view.View
import android.view.inputmethod.InputBinding
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.project.habithero.R
import com.project.habithero.databinding.ActivityRegisterActvityBinding
import com.project.habithero.forgotpassword.ForgotPasswordActivity
import com.project.habithero.login.LoginActivity
import java.util.regex.Pattern


class RegisterActvity : AppCompatActivity() {

    lateinit var binding : ActivityRegisterActvityBinding
    lateinit var auth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityRegisterActvityBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()

        binding.backButton.setOnClickListener{
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        binding.buttonSignup.setOnClickListener{
            val email = binding.etEmailReg.text.toString()
            val username = binding.etUsernameReg.text.toString()
            val phone = binding.etPhoneReg.text.toString()
            val password = binding.etPasswordReg.text.toString()

            if(email.isEmpty()){
                binding.etEmailReg.error = "Email harus diisi"
                binding.etEmailReg.requestFocus()
                return@setOnClickListener
            }

            if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                binding.etEmailReg.error = "Email tidak valid"
                binding.etEmailReg.requestFocus()
                return@setOnClickListener
            }

            if(username.isEmpty()){
                binding.etUsernameReg.error = "Username harus diisi"
                binding.etUsernameReg.requestFocus()
                return@setOnClickListener
            }

            if(phone.isEmpty()){
                binding.etPhoneReg.error = "Nomor telepon harus diisi"
                binding.etPhoneReg.requestFocus()
                return@setOnClickListener
            }

            if(password.isEmpty()){
                binding.etPasswordReg.error = "Password harus diisi"
                binding.etPasswordReg.requestFocus()
                return@setOnClickListener
            }

            if(password.length <= 8) {
                binding.etPasswordReg.error = "Password minimal 8 karakter"
                binding.etPasswordReg.requestFocus()
                return@setOnClickListener
            }
            RegisterFirebase(email,username,phone,password)
        }
    }

    private fun RegisterFirebase(email: String, username: String, phone: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this){
                if (it.isSuccessful){
                    Toast.makeText(this, "Register Berhasil", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, LoginActivity::class.java)
                    startActivity(intent)
                } else {
                    Toast.makeText(this, "${it.exception?.message}",Toast.LENGTH_SHORT).show()
                }
            }
    }
}