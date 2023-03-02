package com.mik0war.complimentsApp

import com.google.gson.annotations.SerializedName

class Compliment (
    @SerializedName("compliment")
    private val compliment : String
){

    suspend fun change(cacheDataSource: CacheDataSource) = cacheDataSource.addOrRemove(compliment, this)

    fun toBaseCompliment() = BaseComplimentUIModel(compliment)

    fun toFavoriteCompliment() = FavoriteComplimentUIModel(compliment)

    fun toRealmModel() = ComplimentRealm().also {
        it.id=compliment
        it.text=compliment
    }
}