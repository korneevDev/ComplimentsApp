package com.mik0war.complimentsApp

import com.google.gson.annotations.SerializedName

data class ComplimentServerModel (
    @SerializedName("compliment")
    private val compliment : String
    ){

    fun change(cacheDataSource: CacheDataSource) = cacheDataSource.addOrRemove(compliment, this)

    fun toBaseCompliment() = BaseCompliment(compliment)

    fun toFavoriteCompliment() = FavoriteCompliment(compliment)
}