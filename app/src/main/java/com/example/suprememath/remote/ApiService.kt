package com.example.suprememath.remote

import com.example.suprememath.models.Image
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("http://api.mathjs.org/v4/")
    fun getMath(
        @Query("expr") expr : String,
    ):Call<Number>

    @GET("http://127.0.0.1:8125/")
    fun getBack(

    ):Call<Image>

}