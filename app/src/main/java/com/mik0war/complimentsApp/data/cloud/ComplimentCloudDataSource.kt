package com.mik0war.complimentsApp.data.cloud

import retrofit2.Call
import retrofit2.Response

class ComplimentCloudDataSource(private val service: ComplimentService) :
    BaseCloudDataSource<ComplimentServerModel>() {
    override fun getServerModel(): Call<ComplimentServerModel> = service.getCompliment()
}

class MockComplimentCloudDataSource : BaseCloudDataSource<ComplimentServerModel>() {
    private var id: Int = -1
    override fun getServerModel(): Call<ComplimentServerModel> =
        object : SimpleCall<ComplimentServerModel> {
            override fun execute(): Response<ComplimentServerModel> {
                return Response.success(
                    ComplimentServerModel(
                        "mockCompliment ${++id}"
                    )
                )
            }
        }
}