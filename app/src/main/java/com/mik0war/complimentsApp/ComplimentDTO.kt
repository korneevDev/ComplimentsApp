package com.mik0war.complimentsApp

import com.google.gson.annotations.SerializedName

data class ComplimentDTO (
    @SerializedName("compliment")
    private val compliment : String
    ){
    fun toCompliment() = BaseCompliment(compliment)
}