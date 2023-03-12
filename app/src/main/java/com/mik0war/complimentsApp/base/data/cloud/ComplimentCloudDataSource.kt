package com.mik0war.complimentsApp.base.data.cloud

import retrofit2.Call

class ComplimentCloudDataSource(private val service: ComplimentService) :
    BaseCloudDataSource<ComplimentServerModel>() {
    override fun getServerModel(): Call<ComplimentServerModel> = service.getCompliment()
}