package com.mik0war.complimentsApp.base.domain

import com.mik0war.complimentsApp.core.domain.FailureHandler
import com.mik0war.complimentsApp.core.presentation.Failure
import com.mik0war.complimentsApp.core.ResourceManager
import com.mik0war.complimentsApp.core.domain.NoConnectionException
import com.mik0war.complimentsApp.core.domain.NoFavorites
import com.mik0war.complimentsApp.core.domain.ServiceUnavailableException
import com.mik0war.complimentsApp.base.presentation.GenericError
import com.mik0war.complimentsApp.base.presentation.NoCachedData
import com.mik0war.complimentsApp.base.presentation.NoConnection
import com.mik0war.complimentsApp.base.presentation.ServiceUnavailable

class FailureFactory(private val resourceManager: ResourceManager) : FailureHandler {
    override fun handle(e: Exception): Failure {
        return when(e){
            is NoFavorites -> NoCachedData(resourceManager)
            is NoConnectionException -> NoConnection(resourceManager)
            is ServiceUnavailableException -> ServiceUnavailable(resourceManager)
            else -> GenericError(resourceManager)
        }
    }

}