package com.mik0war.complimentsApp

interface JokeError{
    fun getErrorMessage() : String
}

class NoConnection(private val resourceManager: ResourceManager) : JokeError {
    override fun getErrorMessage(): String = resourceManager.getString(R.string.no_connection)
}

class ServiceUnavailable(private val resourceManager: ResourceManager) : JokeError {
    override fun getErrorMessage(): String = resourceManager.getString(R.string.service_unavailable)
}



