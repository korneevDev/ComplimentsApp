package com.mik0war.complimentsApp.data.cloud

import retrofit2.Call
import retrofit2.Response

class QuoteCloudDataSource(private val service: QuoteService) :
    BaseCloudDataSource<QuoteServerModel>() {
    override fun getServerModel(): Call<QuoteServerModel> = service.getQuote()
}

class MockQuoteCloudDataSource : BaseCloudDataSource<ComplimentServerModel>() {
    private var id: Int = -1
    override fun getServerModel(): Call<ComplimentServerModel> =
        object : SimpleCall<ComplimentServerModel> {
            override fun execute(): Response<ComplimentServerModel> {
                return Response.success(
                    ComplimentServerModel(
                        "mockQuote ${++id}"
                    )
                )
            }
        }
}
