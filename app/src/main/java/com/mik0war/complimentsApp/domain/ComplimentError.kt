package com.mik0war.complimentsApp.domain

import com.mik0war.complimentsApp.R

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

class GenericError(private val resourceManager: ResourceManager) : ComplimentError {
    override fun getErrorMessage(): String = resourceManager.getString(R.string.default_error_message)
}



