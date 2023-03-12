package com.mik0war.complimentsApp.data

import com.google.gson.annotations.SerializedName
import com.mik0war.complimentsApp.core.Mapper
import retrofit2.Call
import retrofit2.http.GET

interface ComplimentService {
    @GET("https://complimentr.com/api")
    fun getCompliment() : Call<ComplimentServerModel>
}

data class ComplimentServerModel(
    @SerializedName("compliment")
    private val compliment : String
    ) : Mapper<CommonDataModel>{
    override fun to() = CommonDataModel(compliment, compliment)
}


interface QuoteService {
    @GET("https://api.quotable.io/random")
    fun getQuote() : Call<QuoteServerModel>
}

data class QuoteServerModel(
    @SerializedName("_id")
    private val id : String,
    @SerializedName("content")
    private val text : String,
    @SerializedName("author")
    private val author : String
) : Mapper<CommonDataModel>{
    override fun to() = CommonDataModel(id, "$text\n$author")
}