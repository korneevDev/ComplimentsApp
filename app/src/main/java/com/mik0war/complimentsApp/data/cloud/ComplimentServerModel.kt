package com.mik0war.complimentsApp.data.cloud

import com.google.gson.annotations.SerializedName
import com.mik0war.complimentsApp.data.CommonDataModel
import com.mik0war.complimentsApp.core.Mapper

data class ComplimentServerModel(
    @SerializedName("compliment")
    private val compliment : String
    ) : Mapper<CommonDataModel> {
    override fun to() = CommonDataModel(compliment, compliment)
}


