package com.example.training

import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.Window
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.BufferedReader
import java.io.File

class MainActivity : AppCompatActivity() {
    private var stringBuilder:StringBuilder?=null
    lateinit var mService: RetrofitServices
    lateinit var email: EditText
    lateinit var password: EditText
    lateinit var image: ImageView
    lateinit var button: Button
    lateinit var progressBar: ProgressBar
    lateinit var textView: TextView
    private val Context.isConnected: Boolean
        get() {
            return (getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager)
                .activeNetworkInfo?.isConnected == true
        }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        email = findViewById(R.id.login)
        password = findViewById(R.id.password)
        image = findViewById(R.id.imageView)
        button = findViewById(R.id.button)
        progressBar = findViewById(R.id.progressBar)

        val window: Window = this.window
        window.statusBarColor = ContextCompat.getColor(this, R.color.teal_800)
        mService = Common.retrofitService
        image.alpha = 0.toFloat()
        email.alpha = 0.toFloat()
        password.alpha = 0.toFloat()
        button.alpha = 0.toFloat()

        if (isConnected)
        {
            mService.getMovieList().enqueue(object : Callback<MutableList<Movie>> {
                override fun onFailure(call: Call<MutableList<Movie>>, t: Throwable) {

                }
                @SuppressLint("SdCardPath")
                override fun onResponse(call: Call<MutableList<Movie>>, response: Response<MutableList<Movie>>) {
                    val fileName = cacheDir.absolutePath+"/MovieJson.json"
                    val movieList: MutableList<Movie> = response.body() as MutableList<Movie>
                    writeJSONtoFile(fileName, movieList)
                    val test = "/data/user/0/com.example.training/MovieJson.json"
                    writeJSONtoFile(test, movieList)
//                recyclerMovieList.adapter= MyMovieAdapter(baseContext,response.body() as MutableList<Movie>)


                }
            })


            val timer = object: CountDownTimer(2000, 20){
                override fun onTick(p0: Long) {
                }

                override fun onFinish() {
                    val objectAnimator3 = ObjectAnimator.ofFloat(image, "alpha", 0f,1f)
                    objectAnimator3.duration = 500
                    objectAnimator3.start()
                    val objectAnimatorbar = ObjectAnimator.ofFloat(progressBar, "alpha", 1f,0f)
                    objectAnimatorbar.duration = 1
                    objectAnimatorbar.start()
                    val timer = object: CountDownTimer(2000, 20){
                        override fun onTick(p0: Long) {
                        }

                        override fun onFinish() {
                            val objectAnimator = ObjectAnimator.ofFloat(image, "translationX", -330f)
                            objectAnimator.duration = 600
                            objectAnimator.start()
                            val objectAnimator2 = ObjectAnimator.ofFloat(image, "translationY", -760f)
                            objectAnimator2.duration = 600
                            objectAnimator2.start()
                            val objectAnimator4 = ObjectAnimator.ofFloat(image, "scaleX", 1f,0.45f)
                            objectAnimator4.duration = 600
                            objectAnimator4.start()
                            val objectAnimator5 = ObjectAnimator.ofFloat(image, "scaleY", 1f,0.45f)
                            objectAnimator5.duration = 600
                            objectAnimator5.start()
                            val timer = object: CountDownTimer(500, 20){
                                override fun onTick(p0: Long) {
                                }

                                override fun onFinish() {
                                    val objectAnimator6 = ObjectAnimator.ofFloat(button, "alpha", 0f, 1f)
                                    objectAnimator6.duration = 600
                                    objectAnimator6.start()
                                    val objectAnimator7 = ObjectAnimator.ofFloat(email, "alpha", 0f, 1f)
                                    objectAnimator7.duration = 600
                                    objectAnimator7.start()
                                    val objectAnimator8 = ObjectAnimator.ofFloat(password, "alpha", 0f, 1f)
                                    objectAnimator8.duration = 600
                                    objectAnimator8.start()
                                }
                            }
                            timer.start()
                        }
                    }
                    timer.start()
                }
            }
            timer.start()
        }
        else{
            val atert = AlertDialog.Builder(this)
                .setTitle("Error")
                .setMessage("Internet connection is lost!")
                .setPositiveButton("Ok", null)
                .create()
                .show()
        }




    }
    private fun writeJSONtoFile(s:String, movieList: MutableList<Movie>) {
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
    fun login(view: android.view.View) {
//       if (email.text.toString().isNotEmpty() && password.text.toString().isNotEmpty()) {
        val intent = Intent(this, MenuActivity::class.java)
        startActivity(intent)
        finish()
//       }
//       else{
//           val alert = AlertDialog.Builder(this).setTitle("Error")
//               .setPositiveButton("Ok", null)
//               .setMessage("You miss Login or Password!")
//               .create()
//               .show()
//       }
    }

}