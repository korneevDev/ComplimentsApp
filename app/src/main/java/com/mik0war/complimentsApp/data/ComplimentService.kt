package com.mik0war.complimentsApp.data

import retrofit2.http.GET

interface ComplimentService {
    @GET("https://complimentr.com/api")
    suspend fun getCompliment() : ComplimentServerModel
}