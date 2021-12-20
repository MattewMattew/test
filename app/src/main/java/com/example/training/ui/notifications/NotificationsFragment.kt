package com.example.training.ui.notifications

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.training.*
import com.google.gson.Gson
import java.io.BufferedReader
import java.io.File

class NotificationsFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var clear: Button

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = LayoutInflater.from(container?.context).inflate(R.layout.fragment_notifications,container, false)
        clear = view.findViewById(R.id.clearbutton)
        recyclerView = view.findViewById(R.id.recyclerCard)
        recyclerView.layoutManager = LinearLayoutManager(this.context)
        val fileName = activity?.cacheDir?.absolutePath+"/Card.json"
        if (File(fileName).canRead()){
            readJSONfromFile(fileName)
        }
        clear.setOnClickListener {
            val list = mutableListOf<Card>()
            writeJSONtoFileUpdate(fileName,list)
            recyclerView.adapter = MyCardAdapter(list)
//            val intent = Intent(this.activity,MenuActivity::class.java)
//            startActivity(intent)
        }
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
        writeJSONtoFileUpdate(s,read)
    }
    private fun writeJSONtoFileUpdate(s:String, movieList: MutableList<Card>) {
        //Create a Object of Post
        var post = movieList
        //Create a Object of Gson
        var gson = Gson()
        //Convert the Json object to JsonString
        var jsonString: String = gson.toJson(post)
        //Initialize the File Writer and write into file
        val file = File(s)
        file.writeText(jsonString)
    }
}
