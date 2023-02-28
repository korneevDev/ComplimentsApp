package com.mik0war.complimentsApp

interface ComplimentError{
    fun getErrorMessage() : String
}

class NoConnection(private val resourceManager: ResourceManager) : ComplimentError {
    override fun getErrorMessage(): String = resourceManager.getString(R.string.no_connection)
}

class ServiceUnavailable(private val resourceManager: ResourceManager) : ComplimentError {
    override fun getErrorMessage(): String = resourceManager.getString(R.string.service_unavailable)
}

class NoFavoriteCompliments(private val resourceManager: ResourceManager) : ComplimentError {
    override fun getErrorMessage(): String = resourceManager.getString(R.string.no_favorite_compliments)
}



