package com.project.habithero.welcome

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.project.habithero.databinding.ActivityWelcomeBinding
import com.project.habithero.login.LoginActivity
import com.project.habithero.register.RegisterActvity

class WelcomeActivity : AppCompatActivity() {

    lateinit var binding : ActivityWelcomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityWelcomeBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.welcomeSignin.setOnClickListener{
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        binding.welcomeSignup.setOnClickListener{
            val intent = Intent(this, RegisterActvity::class.java)
            startActivity(intent)
        }
    }

}