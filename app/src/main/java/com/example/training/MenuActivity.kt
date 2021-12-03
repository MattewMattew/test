package com.example.training

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
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
    private var stringBuilder:StringBuilder?=null
    lateinit var mService: RetrofitServices
    lateinit var recyclerMovieList: RecyclerView
    lateinit var adapter: MyMovieAdapter
    lateinit var bottomNavigation: BottomNavigationView
    private lateinit var binding: ActivityMenuBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val navView: BottomNavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_activity_menu)
        navView.setupWithNavController(navController)

        recyclerMovieList = findViewById(R.id.recyclerMovieList)
        mService = Common.retrofitService
        recyclerMovieList.layoutManager = LinearLayoutManager(this)
        mService.getMovieList().enqueue(object : Callback<MutableList<Movie>> {
            override fun onFailure(call: Call<MutableList<Movie>>, t: Throwable) {
                Log.d("TAG", "Warning")
            }
            override fun onResponse(call: Call<MutableList<Movie>>, response: Response<MutableList<Movie>>) {
                val fileName = cacheDir.absolutePath+"/MovieJson.json"
                val movieList: MutableList<Movie> = response.body() as MutableList<Movie>
                readJSONfromFile(fileName)
            }
        })
    }

//    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { menuItem ->
//        when (menuItem.itemId)
//        {
//            R.id.navigation_home -> {
//                val toast = Toast.makeText(applicationContext, "Test", Toast.LENGTH_SHORT).show()
////                mService.getMovieList().enqueue(object : Callback<MutableList<Movie>> {
////                    override fun onFailure(call: Call<MutableList<Movie>>, t: Throwable) {
////                        Log.d("TAG", "Warning")
////                    }
////                    override fun onResponse(call: Call<MutableList<Movie>>, response: Response<MutableList<Movie>>) {
////                        val fileName = cacheDir.absolutePath+"/MovieJson.json"
////                        val movieList: MutableList<Movie> = response.body() as MutableList<Movie>
////                        readJSONfromFile(fileName)
////                    }
////                })
//                return@OnNavigationItemSelectedListener true
//            }
//        }
//        false
//    }

    private fun readJSONfromFile(f:String) {

        //Creating a new Gson object to read data
        var gson = GsonBuilder().create()
        //Read the PostJSON.json file
        val bufferedReader: BufferedReader = File(f).bufferedReader()
        // Read the text from bufferReader and store in String variable
        val inputString = bufferedReader.use { it.readText() }
        //Convert the Json File to Gson Object
        var post = gson.fromJson(inputString, Array<Movie>::class.java).toMutableList()
//        //Initialize the String Builder
        Log.d("TAG", "Done")
        recyclerMovieList.adapter= MyMovieAdapter(baseContext,post)
    }
    fun next(view: android.view.View) {

        val intent = Intent(this, MainActivity2::class.java)
        startActivity(intent)

    }



}