package com.example.retrofitapp.Adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofitapp.R
import com.example.retrofitapp.databinding.ItemLayoutBinding
import com.example.retrofitapp.model.Post

class MyAdapter : RecyclerView.Adapter<MyAdapter.MyViewHolder>(){

    private var myList = emptyList<Post>()

    inner class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        private val binding = ItemLayoutBinding.bind(itemView)
        val id = binding.tvId
        val userId = binding.tvUserId
        val title = binding.tvTitle
        val body = binding.tvBody

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = myList[position]
        holder.id.text = currentItem.id.toString()
        holder.userId.text = currentItem.userId.toString()
        holder.title.text = currentItem.title.toString()
        holder.body.text = currentItem.body.toString()
    }

    override fun getItemCount(): Int = myList.size

    @SuppressLint("NotifyDataSetChanged")
    fun setData(newList: List<Post>){
        myList = newList
        notifyDataSetChanged()
    }


}