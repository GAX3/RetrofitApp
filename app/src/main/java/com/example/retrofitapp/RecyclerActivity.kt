package com.example.retrofitapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.retrofitapp.Adapter.MyAdapter
import com.example.retrofitapp.databinding.ActivityRecyclerBinding
import com.example.retrofitapp.model.Post
import com.example.retrofitapp.repository.Repository
import retrofit2.Response

class RecyclerActivity : AppCompatActivity() {

    private lateinit var binding : ActivityRecyclerBinding
    private lateinit var viewModel: MainViewModel
    private val myAdapter by lazy { MyAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecyclerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()

        val repository = Repository()
        val viewModelFactory = MainViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)
        viewModel.getCustomPosts2(2, "id", "desc")
        viewModel.myCustomResponse2.observe(this, Observer { response ->
            if (response.isSuccessful){
                response.body()?.let { myAdapter.setData(it) }
            }else{
                Toast.makeText(this, "${response.code()}", Toast.LENGTH_SHORT).show()
            }

        })
    }

    private fun setupRecyclerView(){
        binding.rvPosts.adapter = myAdapter
        binding.rvPosts.layoutManager = LinearLayoutManager(this)
    }
}