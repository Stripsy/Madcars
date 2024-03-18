package com.example.vehicules

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

const val BASE_URL = "http://s519716619.onlinehome.fr/exchange/madrental/"
interface WebService {
    @GET("get-vehicules.php")
    fun getHeadLines():Call<List<Vehicule>>

}
object WebResponse{
    val webService:WebService
    init {
        val retrofit= Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        webService = retrofit.create(WebService::class.java)
    }
}