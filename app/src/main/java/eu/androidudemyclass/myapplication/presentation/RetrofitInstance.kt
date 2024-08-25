package eu.androidudemyclass.myapplication.presentation

import eu.androidudemyclass.myapplication.data.model.Api
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object RetrofitInstance {

    private val _interceptor:HttpLoggingInterceptor=HttpLoggingInterceptor().apply {
        level=HttpLoggingInterceptor.Level.BODY
    }
    private val client:OkHttpClient=OkHttpClient.Builder()
        .addInterceptor(_interceptor)
        .build()

    val api: Api =Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(Api.BASE_URL)
        .client(client)
        .build()
        .create(Api::class.java)



}