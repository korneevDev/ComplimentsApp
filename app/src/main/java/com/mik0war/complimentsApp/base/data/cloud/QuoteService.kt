package com.mik0war.complimentsApp.base.data.cloud

import retrofit2.Call
import retrofit2.http.GET

interface QuoteService {
    @GET("https://api.quotable.io/random")
    fun getQuote() : Call<QuoteServerModel>
}