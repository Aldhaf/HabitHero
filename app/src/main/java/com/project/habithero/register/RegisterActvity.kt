package com.project.habithero.register

import android.content.Intent
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.view.MenuItem
import android.view.View
import android.view.inputmethod.InputBinding
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.project.habithero.R
import com.project.habithero.databinding.ActivityRegisterActvityBinding
import com.project.habithero.forgotpassword.ForgotPasswordActivity
import com.project.habithero.login.LoginActivity
import java.util.regex.Pattern

class RegisterActvity : AppCompatActivity() {

    lateinit var binding: ActivityRegisterActvityBinding
    lateinit var auth: FirebaseAuth
    private var db = Firebase.firestore

    private lateinit var etName: EditText
    private lateinit var etEmail: EditText
    private lateinit var etUsername: EditText
    private lateinit var etPhone: EditText
    private lateinit var etPassword: EditText
    private lateinit var btnSave: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityRegisterActvityBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()

        etName = binding.etNameReg
        etEmail = binding.etEmailReg
        etUsername = binding.etUsernameReg
        etPhone = binding.etPhoneReg
        etPassword = binding.etPasswordReg
        btnSave = binding.buttonSignup

        binding.backButton.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }



       binding.buttonSignup.setOnClickListener {
            val sName = etName.text.toString()
            val sEmail = etEmail.text.toString()
            val sUsername = etUsername.text.toString()
            val sPhone = etPhone.text.toString()
            val sPassword = etPassword.text.toString()

            if (sName.isEmpty()) {
                binding.etNameReg.error = "Nama harus diisi"
                binding.etNameReg.requestFocus()
                return@setOnClickListener
            } else if (sEmail.isEmpty()) {
                binding.etEmailReg.error = "Email harus diisi"
                binding.etEmailReg.requestFocus()
                return@setOnClickListener
            } else if (sUsername.isEmpty()) {
                binding.etUsernameReg.error = "Username harus diisi"
                binding.etUsernameReg.requestFocus()
                return@setOnClickListener
            } else if (sPhone.isEmpty()) {
                binding.etPhoneReg.error = "Nomor Telepon harus diisi"
                binding.etPhoneReg.requestFocus()
                return@setOnClickListener
            } else if (sPassword.isEmpty()) {
                binding.etPasswordReg.error = "Password harus diisi"
                binding.etPasswordReg.requestFocus()
                return@setOnClickListener
            } else {

            }

            val userMap = hashMapOf(
                "name" to sName,
                "username" to sUsername,
                "phone" to sPhone,
                "email" to sEmail,
                "password" to sPassword
            )

           val userId = auth.currentUser!!.uid

           db.collection("account").add(userMap)
               .addOnSuccessListener {
                    Toast.makeText(this, "Sukses di update", Toast.LENGTH_SHORT).show()
                    etName.text.clear()
                    etEmail.text.clear()
                    etUsername.text.clear()
                    etPhone.text.clear()
                    etPassword.text.clear()
               }
               .addOnFailureListener {
                    Toast.makeText(this, "Failed!", Toast.LENGTH_SHORT).show()
             }

           RegisterFirebase(sName, sEmail, sUsername, sPhone, sPassword)
       }
    }

    private fun RegisterFirebase(
        sName: String,
        sEmail: String,
        sUsername: String,
        sPhone: String,
        sPassword: String
    ) {
        auth.createUserWithEmailAndPassword(sEmail, sPassword)
            .addOnCompleteListener(this) {
                if (it.isSuccessful) {
                    Toast.makeText(this, "Register Berhasil", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, LoginActivity::class.java)
                    startActivity(intent)
                } else {
                    Toast.makeText(this, "Gagal Maning!", Toast.LENGTH_SHORT).show()
                }
            }
    }
}