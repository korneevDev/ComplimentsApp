package com.mik0war.complimentsApp.domain

interface ComplimentInteractor {
    suspend fun getCompliment() : Compliment
    suspend fun changeFavorites() : Compliment
    fun getFavoriteCompliments(flag: Boolean)
}