package com.mik0war.complimentsApp.data.cloud

import com.mik0war.complimentsApp.core.Mapper
import com.mik0war.complimentsApp.core.data.cloud.CloudDataSource
import com.mik0war.complimentsApp.core.domain.NoConnectionException
import com.mik0war.complimentsApp.core.domain.ServiceUnavailableException
import com.mik0war.complimentsApp.data.CommonDataModel
import okhttp3.Request
import okio.Timeout
import retrofit2.Call
import retrofit2.Callback
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

interface SimpleCall<T> : Call<T> {
    override fun clone(): Call<T> = throw IllegalStateException(EXCEPTION_MESSAGE)
    override fun enqueue(callback: Callback<T>) = Unit
    override fun isExecuted(): Boolean = false
    override fun cancel() = Unit
    override fun isCanceled(): Boolean = false
    override fun timeout(): Timeout = throw IllegalStateException(EXCEPTION_MESSAGE)
    override fun request(): Request = throw IllegalStateException(EXCEPTION_MESSAGE)

    companion object {
        const val EXCEPTION_MESSAGE = "Not used"
    }
}