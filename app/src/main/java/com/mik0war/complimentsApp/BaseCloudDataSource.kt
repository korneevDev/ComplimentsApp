package com.mik0war.complimentsApp

import retrofit2.Call
import retrofit2.Response
import java.net.UnknownHostException

class BaseCloudDataSource(private val service: ComplimentService) : CloudDataSource {
    override fun getCompliment(callBack: ComplimentCloudCallBack) {
        service.getCompliment().enqueue(object : retrofit2.Callback<ComplimentServerModel>{
            override fun onResponse(
                call: Call<ComplimentServerModel>,
                response: Response<ComplimentServerModel>
            ) {
                if(response.isSuccessful)
                    callBack.provide(response.body()!!.toCompliment())
                else
                    callBack.fail(ErrorType.SERVICE_UNAVAILABLE)
            }

            override fun onFailure(call: Call<ComplimentServerModel>, t: Throwable) {
                val errorType = if(t is UnknownHostException)
                    ErrorType.NO_CONNECTION
                else
                    ErrorType.SERVICE_UNAVAILABLE

                callBack.fail(errorType)
            }

        })
    }
}