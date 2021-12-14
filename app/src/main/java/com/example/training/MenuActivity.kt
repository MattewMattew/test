package com.example.training

import android.Manifest
import android.Manifest.permission.WRITE_CALENDAR
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.ConnectivityManager
import android.os.Bundle
import android.os.CountDownTimer
import android.os.PersistableBundle
import android.util.Log
import android.view.MenuItem
import android.view.Window
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.get
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.training.databinding.ActivityMenuBinding
import com.example.training.ui.home.HomeFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.BufferedReader
import java.io.File
import java.util.ArrayList

class MenuActivity : AppCompatActivity() {
    lateinit var recyclerText: TextView
    private var stringBuilder: StringBuilder? = null
    lateinit var mService: RetrofitServices
    lateinit var recyclerMovieList: RecyclerView
    lateinit var adapter: MyMovieAdapter
    lateinit var bottomNavigation: BottomNavigationView
    private lateinit var binding: ActivityMenuBinding
    private val Context.isConnected: Boolean
        get() {
            return (getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager)
                .activeNetworkInfo?.isConnected == true
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)





        val window: Window = this.window
        window.statusBarColor = ContextCompat.getColor(this, R.color.white)

        val navView: BottomNavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_activity_menu)
        navView.setupWithNavController(navController)
//        recyclerMovieList = findViewById(R.id.recyclerMovieList)
//        recyclerMovieList.layoutManager = LinearLayoutManager(this)
//        val fileName = cacheDir.absolutePath+"/MovieJson.json"
//        readJSONfromFile(fileName)
    }




    fun next(view: android.view.View) {
        if (isConnected)
        {
            val intent = Intent(this, MainActivity2::class.java)
            startActivity(intent)
        }
    }
}
