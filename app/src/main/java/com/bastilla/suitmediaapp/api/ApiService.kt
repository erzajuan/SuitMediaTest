package com.bastilla.suitmediaapp.api

import retrofit2.Call
import retrofit2.http.GET

interface ApiService {

    @GET("api/users?page=1")
    fun getUser(
//        @Query("page") page: Int
    ): Call<UserResponse>
}