package com.mik0war.complimentsApp.base.data.cloud

import retrofit2.Call
import retrofit2.http.GET

interface ComplimentService {
    @GET("https://complimentr.com/api")
    fun getCompliment() : Call<ComplimentServerModel>
}