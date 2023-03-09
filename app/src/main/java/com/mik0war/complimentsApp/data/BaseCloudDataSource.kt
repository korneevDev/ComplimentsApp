package com.mik0war.complimentsApp.data

import com.mik0war.complimentsApp.domain.NoConnectionException
import com.mik0war.complimentsApp.domain.ServiceUnavailableException
import java.net.UnknownHostException

class BaseCloudDataSource(private val service: ComplimentService) : CloudDataSource {
    override suspend fun getCompliment() : ComplimentDataModel {
        try {
            return service.getCompliment().to()
        } catch (e: Exception) {
            throw if (e is UnknownHostException)
                NoConnectionException()
            else
                ServiceUnavailableException()
        }
    }
}