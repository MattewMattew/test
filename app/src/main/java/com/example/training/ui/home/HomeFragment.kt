package com.example.training.ui.home

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.util.lruCache
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.training.*
import com.example.training.databinding.FragmentHomeBinding
import com.google.gson.GsonBuilder
import java.io.BufferedReader
import java.io.File
import java.text.FieldPosition

class HomeFragment : Fragment() {
    private lateinit var recyclerMovieList: RecyclerView

    // This property is only valid between onCreateView and
    // onDestroyView.

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = LayoutInflater.from(container?.context).inflate(R.layout.fragment_home, container, false)
        recyclerMovieList = view.findViewById(R.id.recyclerMovieList)
        recyclerMovieList.layoutManager = LinearLayoutManager(this.activity)
        val cache: String = activity?.cacheDir.toString()
        Log.d("TAG", cache)
        val fileName = "$cache/MovieJson.json"
        readJSONfromFile(fileName)

        Log.d("TAG", recyclerMovieList.adapter.toString())

        return view
    }
    fun listen(position: Int,post: MutableList<Movie>)
    {
        val alertDialog = AlertDialog.Builder(this.activity)
            val alertview = LayoutInflater.from(this.activity).inflate(R.layout.alert,null)
            alertDialog.setView(alertview)
                .create()
                .show()
//            .setTitle(post[position].name.toString())
//            .setMessage("Real name: " +post[position].realname.toString()
//                    +"\nTeam: "+post[position].team
//                    +"\nFirst appearance: "+post[position].firstapperance.toString()
//                    +"\nCreated by: "+post[position].createdby
//                    +"\nPublisher: "+post[position].publisher
//                    +"\nBio"+post[position].bio)
//            .setPositiveButton("Ok",null)


    }
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
        recyclerMovieList.adapter= MyMovieAdapter(post, clickListener = {listen(it, post)})
    }
}