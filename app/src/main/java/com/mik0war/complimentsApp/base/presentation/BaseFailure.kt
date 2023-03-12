package com.mik0war.complimentsApp.base.presentation

import com.mik0war.complimentsApp.R
import com.mik0war.complimentsApp.core.presentation.Failure
import com.mik0war.complimentsApp.core.ResourceManager


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



