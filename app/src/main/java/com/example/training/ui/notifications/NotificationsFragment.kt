package com.example.training.ui.notifications

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.training.Card
import com.example.training.MyCardAdapter
import com.example.training.R
import com.google.gson.Gson
import java.io.BufferedReader
import java.io.File

class NotificationsFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = LayoutInflater.from(container?.context).inflate(R.layout.fragment_notifications,container, false)
        recyclerView = view.findViewById(R.id.recyclerCard)
        recyclerView.layoutManager = LinearLayoutManager(this.context)
        val fileName = activity?.cacheDir?.absolutePath+"/Card.json"
        readJSONfromFile(fileName)
        return view
    }
    private fun readJSONfromFile(s : String){
        Log.d("TAG","1")
        var gson = Gson()
        Log.d("TAG","2")
        val bufferedReader: BufferedReader = File(s).bufferedReader()
        Log.d("TAG","3")
        var input = bufferedReader.use {it.readText()}
        Log.d("TAG", "4 $input")
        val read = gson.fromJson(input, Array<Card>::class.java).toMutableList()
        Log.d("TAG", "")
        recyclerView.adapter = MyCardAdapter(read)
    }

}
