package com.mik0war.complimentsApp

import android.app.Application
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ComplimentsApp : Application() {

    lateinit var viewModel : ViewModel

    override fun onCreate() {
        super.onCreate()
        val retrofit = Retrofit.Builder()
            .baseUrl("https://www.google.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
//        viewModel = ViewModel(
//                BaseModel(
//                    retrofit.create(ComplimentService::class.java),
//                    BaseResourceManager(this)
//                )

        viewModel = ViewModel(
            TestModel()
        )
    }
}