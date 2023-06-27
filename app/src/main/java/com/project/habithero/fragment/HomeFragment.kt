package com.project.habithero.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.project.habithero.R
import com.project.habithero.login.LoginActivity
import kotlinx.coroutines.Dispatchers.Main

class HomeFragment : BottomSheetDialogFragment() {

    private var db = Firebase.firestore
    lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val etName = view.findViewById<EditText>(R.id.et_namaKegiatan)
        val etFrek = view.findViewById<EditText>(R.id.et_frekuensi)
        val etDate = view.findViewById<EditText>(R.id.et_tanggalKegiatan)
        val btnAdd = view.findViewById<Button>(R.id.button_tambahKegiatan)

        auth = FirebaseAuth.getInstance()

        btnAdd.setOnClickListener{
            val simpanNama = etName.text.toString()
            val simpanFrek = etFrek.text.toString()
            val simpanTanggal = etDate.text.toString()

            val addTask = hashMapOf(
                "nama_kegiatan" to simpanNama,
                "frekuensi" to simpanFrek,
                "tanggal" to simpanTanggal
            )

            val userId = auth.currentUser!!.uid

            db.collection("Kegiatan").add(addTask)
                .addOnSuccessListener { Log.d("Sukses","DocumentSnapshot successfully written!") }
                .addOnFailureListener { e -> Log.w("Error writing document", e) }
        }

    }
}