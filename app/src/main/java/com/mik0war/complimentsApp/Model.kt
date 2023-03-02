package com.mik0war.complimentsApp

interface Model {
    suspend fun getCompliment() : ComplimentUIModel
    suspend fun changeComplimentStatus() : ComplimentUIModel?
    fun chooseDataSource(isCache : Boolean)
}