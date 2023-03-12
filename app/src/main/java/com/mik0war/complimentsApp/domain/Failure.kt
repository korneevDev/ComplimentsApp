package com.mik0war.complimentsApp.domain

import com.mik0war.complimentsApp.R

interface Failure{
    fun getErrorMessage() : String
}

class NoConnection(private val resourceManager: ResourceManager) : Failure {
    override fun getErrorMessage(): String = resourceManager.getString(R.string.no_connection)
}

class ServiceUnavailable(private val resourceManager: ResourceManager) : Failure {
    override fun getErrorMessage(): String = resourceManager.getString(R.string.service_unavailable)
}

class NoCachedData(private val resourceManager: ResourceManager) : Failure {
    override fun getErrorMessage(): String = resourceManager.getString(R.string.no_favorites)
}

class GenericError(private val resourceManager: ResourceManager) : Failure {
    override fun getErrorMessage(): String = resourceManager.getString(R.string.default_error_message)
}



