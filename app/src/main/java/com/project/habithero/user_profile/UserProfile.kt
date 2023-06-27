package com.project.habithero.user_profile

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.project.habithero.R
import com.project.habithero.databinding.ActivityUserProfileBinding
import com.project.habithero.home.HomeActivity
import com.project.habithero.login.LoginActivity

class UserProfile : AppCompatActivity() {

    lateinit var binding: ActivityUserProfileBinding
    lateinit var auth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityUserProfileBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.imgBack.setOnClickListener{
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }

        auth = FirebaseAuth.getInstance()
        binding.logout.setOnClickListener {
            btnLogout()
        }

    }

    private fun btnLogout() {
        val intent = Intent(this,LoginActivity::class.java)
        startActivity(intent)
    }
}