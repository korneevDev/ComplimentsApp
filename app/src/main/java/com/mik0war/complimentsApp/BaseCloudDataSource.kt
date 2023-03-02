package com.mik0war.complimentsApp

import java.net.UnknownHostException

class BaseCloudDataSource(private val service: ComplimentService) : CloudDataSource {
    override suspend fun getCompliment() : Result<ComplimentServerModel, ErrorType> {
        return try{
            val compliment = service.getCompliment()
            Result.Success(compliment)
        } catch (e: Exception){
            val errorType = if(e is UnknownHostException)
                ErrorType.SERVICE_UNAVAILABLE
            else
                ErrorType.NO_CONNECTION

            Result.Error(errorType)
        }
    }
}