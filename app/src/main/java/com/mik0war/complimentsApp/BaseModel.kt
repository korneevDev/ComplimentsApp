package com.mik0war.complimentsApp

import retrofit2.Call
import retrofit2.Response
import java.net.UnknownHostException

class BaseModel(
    private val complimentService: ComplimentService,
    private val resourceManager: ResourceManager
    ) : Model {
    private var resultCallBack : ResultCallBack? = null
    private val noConnection by lazy { NoConnection(resourceManager) }
    private val serviceUnavailable by lazy {ServiceUnavailable(resourceManager)}

    override fun getCompliment() {
        complimentService.getCompliment().enqueue(object : retrofit2.Callback<ComplimentDTO>{
            override fun onResponse(call: Call<ComplimentDTO>, response: Response<ComplimentDTO>) {
                if(response.isSuccessful)
                    resultCallBack?.provideCompliment(response.body()!!.toCompliment())
                else
                    resultCallBack?.provideCompliment(FailedCompliment(serviceUnavailable.getErrorMessage()))
            }

            override fun onFailure(call: Call<ComplimentDTO>, t: Throwable) {
                if(t is UnknownHostException)
                    resultCallBack?.provideCompliment(FailedCompliment(noConnection.getErrorMessage()))
                else
                    resultCallBack?.provideCompliment(FailedCompliment(serviceUnavailable.getErrorMessage()))
            }


        })
    }

    override fun init(callBack: ResultCallBack) {
        this.resultCallBack = callBack
    }

    override fun clear() {
        this.resultCallBack = null
    }
}