package com.mik0war.complimentsApp

import android.app.Application
import io.realm.Realm
import io.realm.RealmConfiguration
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ComplimentsApp : Application() {

    lateinit var viewModel : ViewModel

    override fun onCreate() {
        super.onCreate()
        Realm.init(this)
        val conf = RealmConfiguration.Builder().allowWritesOnUiThread(true).build()
        Realm.setDefaultConfiguration(conf)

        val retrofit = Retrofit.Builder()
            .baseUrl("https://www.google.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        viewModel = ViewModel(
            BaseModel(
                cacheDataSource=BaseCacheDataSource(Realm.getDefaultInstance()),
                cloudDataSource=BaseCloudDataSource(
                    retrofit.create(ComplimentService::class.java)),
                resourceManager = BaseResourceManager(this))
        )
    }
}