package com.mik0war.complimentsApp.domain

interface FailureHandler {
    fun handle(e : Exception) : Failure
}

class FailureFactory(private val resourceManager: ResourceManager) : FailureHandler{
    override fun handle(e: Exception): Failure {
        return when(e){
            is NoFavorites -> NoCachedData(resourceManager)
            is NoConnectionException -> NoConnection(resourceManager)
            is ServiceUnavailableException -> ServiceUnavailable(resourceManager)
            else -> GenericError(resourceManager)
        }
    }

}