package com.mik0war.complimentsApp.data

interface Repository {
    suspend fun getCompliment() : ComplimentDataModel
    suspend fun changeComplimentStatus() : ComplimentDataModel
    fun chooseDataSource(isCache : Boolean)
}