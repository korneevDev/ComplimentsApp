package com.mik0war.complimentsApp.data

import com.google.gson.annotations.SerializedName
import com.mik0war.complimentsApp.core.Mapper
import retrofit2.http.GET

interface ComplimentService {
    @GET("https://complimentr.com/api")
    suspend fun getCompliment() : ComplimentServerModel
}

data class ComplimentServerModel(
    @SerializedName("compliment")
    private val compliment : String
    ) : Mapper<ComplimentDataModel>{
    override fun to() = ComplimentDataModel(compliment)
}