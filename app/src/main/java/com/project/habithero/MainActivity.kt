package com.project.habithero

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.project.habithero.adapter.ViewPagerAdapter
import com.project.habithero.databinding.ActivityMainBinding
import com.project.habithero.fragment.ForumFragment
import com.project.habithero.fragment.HomeFragment
import com.project.habithero.fragment.ProfileFragment
import com.project.habithero.home.HomeActivity

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    private lateinit var bottomNav : BottomNavigationView
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

//        bottomNav = findViewById(R.id.bottom_nav)
//
//        bottomNav.setOnItemSelectedListener { item ->
//            when (item.itemId) {
//                R.id.home ->
//                    true
//
//                R.id.forum ->
//                    // do something here
//                    true
//                R.id.profile ->
//                    // do something here
//                    true
//                else -> false
//            }
//        }
//
//    }
//    fun home(){
//        val intent = Intent(this, HomeActivity::class.java)
//        startActivity(intent)
    }

}