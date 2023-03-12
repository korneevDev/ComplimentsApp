package com.mik0war.complimentsApp.base.data.cloud

import retrofit2.Call

class QuoteCloudDataSource(private val service: QuoteService) :
    BaseCloudDataSource<QuoteServerModel>() {
    override fun getServerModel(): Call<QuoteServerModel> = service.getQuote()
}