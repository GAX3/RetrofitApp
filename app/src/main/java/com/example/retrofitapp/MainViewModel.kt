package com.example.retrofitapp

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.retrofitapp.model.Post
import com.example.retrofitapp.repository.Repository
import kotlinx.coroutines.launch
import retrofit2.Response


class MainViewModel(private val repository: Repository) : ViewModel() {

    val myResponse: MutableLiveData<Response<Post>> = MutableLiveData()
    val myResponse2: MutableLiveData<Response<Post>> = MutableLiveData()
    val myCustomResponse: MutableLiveData<Response<List<Post>>> = MutableLiveData()
    val myCustomResponse2: MutableLiveData<Response<List<Post>>> = MutableLiveData()
    val myCustomQueryMap: MutableLiveData<Response<List<Post>>> = MutableLiveData()

    val myResponsePost: MutableLiveData<Response<Post>> = MutableLiveData()
    val myResponsePostForm: MutableLiveData<Response<Post>> = MutableLiveData()

    fun getPost(){
        viewModelScope.launch {
            val response = repository.getPost()
            myResponse.value = response
        }
    }

    fun getPost2(number: Int){
        viewModelScope.launch {
            val response = repository.getPost2(number)
            myResponse2.value = response
        }
    }

    fun getCustomPosts(userId: Int){
        viewModelScope.launch {
            val response = repository.getCustomPosts(userId)
            myCustomResponse.value = response
        }
    }

    fun getCustomPosts2(userId: Int, sort: String, order: String){
        viewModelScope.launch {
            val response = repository.getCustomPosts2(userId, sort, order)
            myCustomResponse2.value = response
        }
    }

    fun getCustomQuery(userId: Int, option: Map<String, String>){
        viewModelScope.launch {
            val response = repository.getCustomQuery(userId, option)
            myCustomQueryMap.value = response
        }
    }

    fun pushPost(post: Post){
        viewModelScope.launch {
            val response = repository.pushPost(post)
            myResponsePost.value = response
        }
    }

    fun pushPostForm(userId: Int, id : Int, title: String, body: String){
        viewModelScope.launch {
            val response = repository.pushPostForm(userId, id, title, body)
            myResponsePostForm.value = response
        }
    }



}