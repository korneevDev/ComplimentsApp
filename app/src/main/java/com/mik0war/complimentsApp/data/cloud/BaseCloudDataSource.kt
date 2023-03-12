package com.mik0war.complimentsApp.data.cloud

import com.mik0war.complimentsApp.core.Mapper
import com.mik0war.complimentsApp.core.data.cloud.CloudDataSource
import com.mik0war.complimentsApp.core.domain.NoConnectionException
import com.mik0war.complimentsApp.core.domain.ServiceUnavailableException
import com.mik0war.complimentsApp.data.CommonDataModel
import retrofit2.Call
import java.net.UnknownHostException

abstract class BaseCloudDataSource<T : Mapper<CommonDataModel>> : CloudDataSource {
    protected abstract fun getServerModel() : Call<T>
    override suspend fun getData() : CommonDataModel {
        try {
            return getServerModel().execute().body()!!.to()
        } catch (e: Exception) {
            throw if (e is UnknownHostException)
                NoConnectionException()
            else
                ServiceUnavailableException()
        }
    }
}