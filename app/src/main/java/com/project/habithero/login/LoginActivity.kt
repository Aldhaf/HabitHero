package com.project.habithero.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.project.habithero.MainActivity
import com.project.habithero.R
import com.project.habithero.databinding.ActivityLoginBinding
import com.project.habithero.forgotpassword.ForgotPasswordActivity
import com.project.habithero.fragment.HomeFragment
import com.project.habithero.home.HomeActivity
import com.project.habithero.register.RegisterActvity

class LoginActivity : AppCompatActivity() {

    lateinit var binding : ActivityLoginBinding
    lateinit var auth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityLoginBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()

        binding.tvSignup.setOnClickListener{
            val intent = Intent(this, RegisterActvity::class.java)
            startActivity(intent)
        }

        binding.buttonForgotPassword.setOnClickListener{
            val intent = Intent(this, ForgotPasswordActivity::class.java)
            startActivity(intent)
        }

        binding.buttonLogin.setOnClickListener{
            val email = binding.etEmailLog.text.toString()
            val password = binding.etPasswordLog.text.toString()

            if(email.isEmpty()){
                binding.etEmailLog.error = "Email harus diisi"
                binding.etEmailLog.requestFocus()
                return@setOnClickListener
            }

            if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                binding.etEmailLog.error = "Email tidak valid"
                binding.etEmailLog.requestFocus()
                return@setOnClickListener
            }

            if(password.isEmpty()){
                binding.etPasswordLog.error = "Password harus diisi"
                binding.etPasswordLog.requestFocus()
                return@setOnClickListener
            }

            LoginFirebase(email,password)
        }

    }

    private fun LoginFirebase(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this){
                if (it.isSuccessful){
                    Toast.makeText(this, "Login Berhasil", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, HomeActivity::class.java)
                    startActivity(intent)
                } else {
                    Toast.makeText(this, "gagal login", Toast.LENGTH_SHORT).show()
                }
            }
    }
}