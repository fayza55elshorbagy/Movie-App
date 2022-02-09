package com.example.movieapp.ui

import android.content.IntentFilter
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.movieapp.R
import com.example.movieapp.databinding.ActivityMainBinding
import com.example.movieapp.ui.base.NetworkChangeReceiver
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity() {

    lateinit var netwokReciever : NetworkChangeReceiver
    lateinit var mainBinding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        val view = mainBinding.root
        setContentView(view)

    netwokReciever = NetworkChangeReceiver(this)
        this.registerReceiver(netwokReciever, IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"))

        val bottomNavigationView = findViewById<BottomNavigationView>(com.example.movieapp.R.id.bottom_nav)
        val navHostFragment = supportFragmentManager.findFragmentById(com.example.movieapp.R.id.nav_host_fragment)
        val navController = navHostFragment?.findNavController()
        if (navController != null) {
            bottomNavigationView.setupWithNavController(navController!!)
        }

    }


    override fun onDestroy() {
        super.onDestroy()
        this.unregisterReceiver(netwokReciever)
    }

}