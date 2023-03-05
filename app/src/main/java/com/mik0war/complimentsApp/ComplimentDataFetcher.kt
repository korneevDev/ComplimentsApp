package com.mik0war.complimentsApp

interface ComplimentDataFetcher <T, E> {
    suspend fun getCompliment() : Result<T, E>
}

interface CloudDataSource : ComplimentDataFetcher<ComplimentServerModel, ErrorType>

interface CacheDataSource : ChangeComplimentStatus, ComplimentDataFetcher<Compliment, Unit>