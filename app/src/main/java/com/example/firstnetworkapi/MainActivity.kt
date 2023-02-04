package com.example.firstnetworkapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.firstnetworkapi.databinding.ActivityMainBinding
import com.example.firstnetworkapi.di.SchoolsApp

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {

    private var binding: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =  ActivityMainBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_main)
        SchoolsApp.schoolsComponent.inject(this)

//        val navHost = supportFragmentManager.findFragmentById(R.id.frag_container) as NavHostFragment
//        setupActionBarWithNavController(navHost.navController)



    }

//    override fun onSupportNavigateUp(): Boolean {
//        //return findNavController()
//    }

//    override fun onDestroy() {
//        super.onDestroy()
//        binding = null
//    }
}