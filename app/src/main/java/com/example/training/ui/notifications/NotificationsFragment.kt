package com.example.training.ui.notifications

import android.annotation.SuppressLint
import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
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
    private lateinit var image: ImageView
    private lateinit var emptyText: TextView
    var t: Float = 1F
    var b: Float = 0F

    @SuppressLint("WrongConstant")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = LayoutInflater.from(container?.context).inflate(R.layout.fragment_notifications,container, false)
        clear = view.findViewById(R.id.clearbutton)
        image = view.findViewById(R.id.emptyimage)
        if (resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK == Configuration.UI_MODE_NIGHT_YES) {
            Log.d("TAG", "YES")
            image.setImageResource(R.drawable.ic_book_svgrepo_com__2_white)
        }
        emptyText = view.findViewById(R.id.emptytext)
        recyclerView = view.findViewById(R.id.recyclerCard)
        recyclerView.layoutManager = LinearLayoutManager(this.context)
        val fileName = activity?.cacheDir?.absolutePath+"/Card.json"
        if (File(fileName).canRead()){
            readJSONfromFile(fileName)
        }

        if (readJSONfromFileUpdate(fileName).toString() == "[]")
        {
            image.alpha = 1F
            emptyText.alpha = 1F
            clear.alpha = 0.toFloat()
        }
        clear.setOnClickListener {
            val list = mutableListOf<Card>()
            writeJSONtoFileUpdate(fileName,list)
            val timer = object :CountDownTimer(1000,10){
                override fun onTick(p0: Long) {
                    t -= 0.05.toFloat()
                    recyclerView.alpha = t
                    clear.alpha = t
                }

                override fun onFinish() {
                    recyclerView.adapter = MyCardAdapter(list)
                    val timer = object :CountDownTimer(1000,10){
                        override fun onTick(p0: Long) {
                            b += 0.05.toFloat()
                            image.alpha = b
                            emptyText.alpha = b
                        }

                        override fun onFinish() {

                        }
                    }
                    timer.start()
                }
            }
            timer.start()

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
    private fun readJSONfromFileUpdate(s : String): MutableList<Card> {
        var gson = Gson()
        val bufferedReader: BufferedReader = File(s).bufferedReader()
        var input = bufferedReader.use {it.readText()}
        val read = gson.fromJson(input, Array<Card>::class.java).toMutableList()
        return read
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
