package com.mik0war.complimentsApp

interface CacheDataSource {
    suspend fun getCompliment() : Result<Compliment, Unit>
    suspend fun addOrRemove(id : String, compliment: Compliment) : ComplimentUIModel
}