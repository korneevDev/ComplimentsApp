package com.mik0war.complimentsApp.data

interface ComplimentDataFetcher {
    suspend fun getCompliment() : ComplimentDataModel
}

interface CloudDataSource : ComplimentDataFetcher
interface CacheDataSource : ComplimentDataFetcher, ChangeComplimentStatus