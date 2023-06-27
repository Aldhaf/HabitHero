package com.project.habithero.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.EventListener
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.project.habithero.R
import com.project.habithero.Task
import com.project.habithero.User
import com.project.habithero.databinding.ActivityHomeBinding
import com.project.habithero.adapter.HomeAdapter
import com.project.habithero.fragment.HomeFragment
import com.project.habithero.login.LoginActivity
import com.project.habithero.user_profile.UserProfile
import org.checkerframework.common.returnsreceiver.qual.This
import java.text.DateFormat
import java.util.Calendar

class HomeActivity : AppCompatActivity() {

    lateinit var binding : ActivityHomeBinding
    private lateinit var db : FirebaseFirestore
    lateinit var auth: FirebaseAuth

    private lateinit var recyclerView: RecyclerView
    private lateinit var taskArrayList: ArrayList<Task>
    private lateinit var myAdapter: HomeAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityHomeBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val calendar = Calendar.getInstance().time
        val dateFormat = DateFormat.getDateInstance(DateFormat.DEFAULT).format(calendar)
        val timeFormat = DateFormat.getTimeInstance().format(calendar)

        val bottom = findViewById<Button>(R.id.button_mulai)
        val bottomSheetFragment = HomeFragment()

        bottom.setOnClickListener{
            bottomSheetFragment.show(supportFragmentManager,"button sukses")
        }

        binding.imgProfile.setOnClickListener {
            val intent = Intent(this, UserProfile::class.java)
            startActivity(intent)
        }


        val dateTv = findViewById<TextView>(R.id.tvDate)
        val timeTv = findViewById<TextView>(R.id.tvTime)
        dateTv.text = dateFormat
        timeTv.text = timeFormat


        auth = FirebaseAuth.getInstance()
        val userId = auth.currentUser?.uid


        recyclerView = findViewById(R.id.recycleView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)

        taskArrayList = arrayListOf()

        myAdapter = HomeAdapter(taskArrayList)

        recyclerView.adapter = myAdapter

        EventChangeListener()

    }


    private fun EventChangeListener() {
        db = FirebaseFirestore.getInstance()
        db.collection("Kegiatan").
                addSnapshotListener(object : EventListener<QuerySnapshot>{
                    override fun onEvent(
                        value: QuerySnapshot?,
                        error: FirebaseFirestoreException?
                    ) {

                        if (error != null) {
                            Log.e("Firestore ERROR",error.message.toString())
                            return
                        }

                        for (dc : DocumentChange in value?.documentChanges!!){
                            if (dc.type == DocumentChange.Type.ADDED) {
                                taskArrayList.add(dc.document.toObject(Task::class.java))
                            }
                        }

                        myAdapter.notifyDataSetChanged()

                    }
                })
    }
}