package com.mik0war.complimentsApp

interface CloudDataSource {
    suspend fun getCompliment() : Result<ComplimentServerModel, ErrorType>
}