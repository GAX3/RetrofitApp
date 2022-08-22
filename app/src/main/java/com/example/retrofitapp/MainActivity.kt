package com.example.retrofitapp

import android.content.Intent
import android.icu.number.IntegerWidth
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.retrofitapp.databinding.ActivityMainBinding
import com.example.retrofitapp.model.Post
import com.example.retrofitapp.repository.Repository

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val repository = Repository()
        val viewModelFactory = MainViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)

        binding.btnSearch2.setOnClickListener {
            viewModel.getPost()
            viewModel.myResponse.observe(this, Observer { response ->
                if (response.isSuccessful){
                    Log.i("Response", response.body()?.userId.toString())
                    Log.i("Response", response.body()?.id.toString())
                    Log.i("Response", response.body()?.title!!)
                    Log.i("Response", response.body()?.body!!)
                    //Header
                    Log.i("Response", response.headers().toString())
                    binding.tvHello.text = response.body()?.title
                }else{
                    Log.i("Response", response.errorBody().toString())
                    binding.tvHello.text = response.code().toString()
                }
            })
        }

        binding.btnSearch.setOnClickListener {
            val myNumber = binding.edtNumber.text.toString()

            viewModel.getPost2(Integer.parseInt(myNumber))

            viewModel.myResponse2.observe(this, Observer { response ->
                if (response.isSuccessful){
                    binding.tvHello.text = response.body().toString()
                }else{
                    binding.tvHello.text = response.code().toString()
                }
            })
        }

        binding.btnSearchFilter.setOnClickListener {
            val myNumber = binding.edtNumber.text.toString()

            viewModel.getCustomPosts(Integer.parseInt(myNumber))

            viewModel.myCustomResponse.observe(this, Observer { response ->
                if (response.isSuccessful){
                    binding.tvHello.text = response.code().toString()
                    response.body()!!.forEach {
                        Log.i("Response", it.userId.toString())
                        Log.i("Response", it.id.toString())
                        Log.i("Response", it.title.toString())
                        Log.i("Response", it.body.toString())
                        Log.i("Response", "" )
                    }
                }else{
                    binding.tvHello.text = response.code().toString()
                }
            })
        }

        binding.btnSearchFilter2.setOnClickListener {
            val myNumber = binding.edtNumber.text.toString()

            viewModel.getCustomPosts2(Integer.parseInt(myNumber), "id", "desc")
            viewModel.myCustomResponse2.observe(this, Observer { response ->
                if (response.isSuccessful){
                    binding.tvHello.text = response.code().toString()
                    response.body()!!.forEach {
                        Log.i("Response", it.userId.toString())
                        Log.i("Response", it.id.toString())
                        Log.i("Response", it.title.toString())
                        Log.i("Response", it.body.toString())
                        Log.i("Response", "" )
                    }
                }else{
                    binding.tvHello.text = response.code().toString()
                }
            })

        }


        val options: HashMap<String, String> = HashMap()

        options["_sort"] = "id"
        options["_order"]= "desc"

        binding.btnQueryMAp.setOnClickListener {
            val myNumber = binding.edtNumber.text.toString()

            viewModel.getCustomQuery(Integer.parseInt(myNumber), options)
            viewModel.myCustomQueryMap.observe(this, Observer { response ->
                if (response.isSuccessful){
                    binding.tvHello.text = response.code().toString()
                    response.body()!!.forEach {
                        Log.i("Response", it.userId.toString())
                        Log.i("Response", it.id.toString())
                        Log.i("Response", it.title.toString())
                        Log.i("Response", it.body.toString())
                        Log.i("Response", "" )
                    }
                }else{
                    binding.tvHello.text = response.code().toString()
                }
            })
        }

        binding.btnNext.setOnClickListener {
                startActivity(Intent(this, RecyclerActivity::class.java))
        }

        binding.btnPost.setOnClickListener {
            val myPost = Post(2,2, "Title", "Body")
            viewModel.pushPost(myPost)
            viewModel.myResponsePost.observe(this, Observer { response ->
                if (response.isSuccessful){
                    binding.tvHello.text = response.code().toString()
                    Log.i("Post", response.body().toString())
                    Log.i("Post", response.code().toString())
                    Log.i("Post", response.message())
                }else{
                    binding.tvHello.text = response.code().toString()
                }
            })

        }

        binding.btnPostForm.setOnClickListener {
            viewModel.pushPostForm(2,2, "title", "body")
            viewModel.myResponsePost.observe(this, Observer { response ->
                if (response.isSuccessful){
                    binding.tvHello.text = response.code().toString()
                    Log.i("Post", response.body().toString())
                    Log.i("Post", response.code().toString())
                    Log.i("Post", response.message())
                }else{
                    binding.tvHello.text = response.code().toString()
                }
            })

        }




    }
}