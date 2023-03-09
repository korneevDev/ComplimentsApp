package com.mik0war.complimentsApp.domain

import com.mik0war.complimentsApp.*

interface ComplimentFailureHandler {
    fun handle(e : Exception) : ComplimentError
}

class ComplimentFailureFactory(private val resourceManager: ResourceManager) : ComplimentFailureHandler{
    override fun handle(e: Exception): ComplimentError {
        return when(e){
            is NoFavoriteComplimentsException -> NoFavoriteCompliments(resourceManager)
            is NoConnectionException -> NoConnection(resourceManager)
            is ServiceUnavailableException -> ServiceUnavailable(resourceManager)
            else -> GenericError(resourceManager)
        }
    }

}