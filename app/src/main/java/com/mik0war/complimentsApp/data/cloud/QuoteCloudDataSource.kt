package com.mik0war.complimentsApp.data.cloud

import retrofit2.Call

class QuoteCloudDataSource(private val service: QuoteService) :
    BaseCloudDataSource<QuoteServerModel>() {
    override fun getServerModel(): Call<QuoteServerModel> = service.getQuote()
}