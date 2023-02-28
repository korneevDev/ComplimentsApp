package com.mik0war.complimentsApp

import retrofit2.Call
import retrofit2.http.GET

interface ComplimentService {
    @GET("https://complimentr.com/api")
    fun getCompliment() : Call<ComplimentServerModel>
}