package com.example.retrofitapp.api

import com.example.retrofitapp.model.Post
import retrofit2.Response
import retrofit2.http.*

interface SimpleApi {

    //@Header custom dynamically es pasando la info de header como parametro

    //Simple Get
    //@Header custom
    @Headers(
        "Authorization: 123123123",
        "Platform: Android"
    )
    @GET("posts/1/")
    suspend fun getPost(): Response<Post>

    //Path Filtro individual
    @GET("posts/{postNumber}/")
    suspend fun getPost2(
        @Path("postNumber") number: Int
    ):Response <Post>

    //Simple Query
    @GET("posts")
    suspend fun getCustomPosts(
        @Query("userId") userId: Int
    ): Response <List<Post>>

    //multiple Query
    @GET("posts")
    suspend fun getCustomPosts2(
        @Query("userId") userId: Int,
        @Query("_sort") sort: String,
        @Query("_order") order: String
    ): Response <List<Post>>

    //Query Map
    @GET("posts")
    suspend fun getCustomQuery(
        @Query("userId") userId: Int,
        @QueryMap options: Map<String, String>
    ): Response <List<Post>>

    @POST("posts")
    suspend fun pushPost(
        @Body post: Post
    ): Response <Post>

    @FormUrlEncoded
    @POST("posts")
    suspend fun pushPostForm(
        @Field("userId") userId: Int,
        @Field("id") id: Int,
        @Field("title") title: String,
        @Field("body") body: String
    ): Response<Post>


}


















