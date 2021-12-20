package com.example.training

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class MyCardAdapter(private val movieList: MutableList<Card>):
    RecyclerView.Adapter<MyCardAdapter.MyCardViewHolder>() {
    class MyCardViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var name: TextView? = itemView.findViewById(R.id.txt_name_alert_elem)
        val amount: TextView? = itemView.findViewById(R.id.txt_amount_elem)
        val image: ImageView? = itemView.findViewById(R.id.image_movie_alert_elem)
    }

    override fun getItemCount() = movieList.size

    override fun onBindViewHolder(holder: MyCardViewHolder, position: Int) {
        holder.name?.text = movieList[position].name
        holder.amount?.text = movieList[position].amount
        Picasso.get().load(movieList[position].url).into(holder.image)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyCardViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.layout,parent,false)
        return MyCardViewHolder(itemView)
    }
}