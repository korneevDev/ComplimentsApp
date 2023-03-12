package com.mik0war.complimentsApp.base.data.cloud

import com.google.gson.annotations.SerializedName
import com.mik0war.complimentsApp.core.Mapper
import com.mik0war.complimentsApp.base.data.CommonDataModel

data class QuoteServerModel(
    @SerializedName("_id")
    private val id : String,
    @SerializedName("content")
    private val text : String,
    @SerializedName("author")
    private val author : String
) : Mapper<CommonDataModel> {
    override fun to() = CommonDataModel(id, "$text\n$author")
}