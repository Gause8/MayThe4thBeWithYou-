package com.example.maythefourthbewithyou.network

import com.example.maythefourthbewithyou.network.response.PersonResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface Api {

    /*
    Restful call to GET the list of people
     */
    @GET("api/people/")
    suspend fun getPeople():Response<PersonResponse>

    @GET("api/people/")
    suspend fun getNextPage(@Query("page")page: String): Response<PersonResponse>


    /*
    basic retrofit using a companion object to only create one instance of the retrofit
    adding logging to see the info for the network call
     */
    companion object{
        operator fun invoke(): Api{
            val logging = HttpLoggingInterceptor()
            logging.setLevel(HttpLoggingInterceptor.Level.BASIC)
            val client: OkHttpClient = OkHttpClient.Builder().addInterceptor(logging).build()

            return Retrofit.Builder().baseUrl("https://swapi.dev/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
                .create(Api::class.java)

        }
    }
}