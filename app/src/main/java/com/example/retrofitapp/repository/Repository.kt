package com.example.retrofitapp.repository

import android.icu.text.CaseMap
import com.example.retrofitapp.api.RetrofitInstance
import com.example.retrofitapp.model.Post
import retrofit2.Response

class Repository {

    suspend fun getPost(): Response<Post> {
        return RetrofitInstance.api.getPost()
    }

    suspend fun getPost2(number: Int): Response<Post>{
        return RetrofitInstance.api.getPost2(number)
    }

    suspend fun getCustomPosts(userId: Int): Response<List<Post>>{
        return RetrofitInstance.api.getCustomPosts(userId)
    }

    suspend fun getCustomPosts2(userId: Int, sort: String, order: String): Response<List<Post>>{
        return RetrofitInstance.api.getCustomPosts2(userId, sort, order)
    }

    suspend fun getCustomQuery(userId: Int, options: Map<String, String>): Response<List<Post>>{
        return RetrofitInstance.api.getCustomQuery(userId, options)
    }

    suspend fun pushPost(post: Post): Response<Post>{
        return RetrofitInstance.api.pushPost(post)
    }

    suspend fun pushPostForm(userId: Int, id: Int, title: String, body: String): Response<Post>{
        return RetrofitInstance.api.pushPostForm(userId, id, title, body)
    }

}