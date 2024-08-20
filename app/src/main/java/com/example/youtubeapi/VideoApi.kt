package com.example.youtubeapi

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface VideoApi {
    @GET("search")
    fun getSearchResults(
        @Query("key") apiKey: String,
        @Query("part") part: String = "snippet",
        @Query("q") query: String,
        @Query("regionCode") regionCode: String = "TR",
        @Query("maxResults") maxResults: Int = 10
    ): Call<SearchListResponse>
}