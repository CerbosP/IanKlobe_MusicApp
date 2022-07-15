package com.example.ianklobe_musicapp.network

import com.example.ianklobe_musicapp.data.MusicResponse
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    // This retrieves the api based on the search command
    // term gets replaced with whatever fragment is calling it.
    @GET("search")
    fun getSongLists(
        @Query("term") musicType: String,
        @Query("amp;media") mediaType: String =  "music",
        @Query("amp;entity") entityType: String =  "song",
        @Query("amp;limit") limitNumber: Int =  50
    ): Call<MusicResponse>


    companion object {
        private var retrofit: Retrofit? = null

        // This will get the instance of retrofit for the API
        fun getRetrofitInstance(): Retrofit {
            if(retrofit == null) {
                retrofit = Retrofit.Builder()
                    .baseUrl("https://itunes.apple.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
            return retrofit!!
        }
    }
}