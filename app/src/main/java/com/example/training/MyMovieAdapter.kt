package com.example.training

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import org.json.JSONObject

class MyMovieAdapter(private val movieList: MutableList<Movie>, val clickListener: (Int) -> Unit):
    RecyclerView.Adapter<MyMovieAdapter.MyViewHolder>()  {


    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val image: ImageView = itemView.findViewById(R.id.image_movie)
        val txt_name: TextView = itemView.findViewById(R.id.txt_name)
        val txt_team: TextView = itemView.findViewById(R.id.txt_team)
        val txt_createdby: TextView = itemView.findViewById(R.id.txt_createdby)
        val txt_published: TextView = itemView.findViewById(R.id.published_item)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.element, parent, false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount() = movieList.size



    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        Picasso.get().load(movieList[position].imageurl).into(holder.image)
        holder.txt_name.text = movieList[position].name
        holder.txt_team.text = movieList[position].team
        holder.txt_createdby.text = movieList[position].createdby
        holder.txt_published.text = movieList[position].publisher
        holder.itemView.setOnClickListener{
            clickListener(position)
        }

    }
}