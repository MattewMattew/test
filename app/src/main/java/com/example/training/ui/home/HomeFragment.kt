package com.example.training.ui.home

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.text.Html
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
        val fileName = "$cache/MovieJson.json"
        readJSONfromFile(fileName)


        return view
    }
    @SuppressLint("SetTextI18n", "InflateParams")
    fun listen(position: Int, post: MutableList<Movie>)
    {
        val alertview = LayoutInflater.from(this.activity).inflate(R.layout.alert,null)
        val name = alertview.findViewById<TextView>(R.id.name)
        val realname = alertview.findViewById<TextView>(R.id.realname)
        val team = alertview.findViewById<TextView>(R.id.team)
        val createdby = alertview.findViewById<TextView>(R.id.createdby)
        val publisher = alertview.findViewById<TextView>(R.id.publisher)
        val bio = alertview.findViewById<TextView>(R.id.bio)
        val button = alertview.findViewById<TextView>(R.id.buttonalert)
        name.text = post[position].name
        realname.text = Html.fromHtml("<b>Real name: </b>" + post[position].realname)
        team.text = Html.fromHtml("<b>Team: </b>" + post[position].team)
        createdby.text = Html.fromHtml("<b>Created by: </b>" + post[position].createdby)
        publisher.text = Html.fromHtml("<b>Publisher: </b>" + post[position].publisher)
        bio.text = Html.fromHtml("<b>Biography: </b>" + post[position].bio)


        val customDialog = Dialog(requireActivity())
        customDialog.setContentView(alertview)
        customDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        customDialog.show()
        button.setOnClickListener{
            customDialog.dismiss()
        }

//        val alertDialog = AlertDialog.Builder(this.activity)
//            .setView(alertview)
//            .create()
//            .show()


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