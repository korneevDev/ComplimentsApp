package com.mik0war.complimentsApp.presentation

import android.app.Application
import com.mik0war.complimentsApp.data.*
import com.mik0war.complimentsApp.domain.BaseComplimentInteractor
import com.mik0war.complimentsApp.domain.BaseResourceManager
import com.mik0war.complimentsApp.domain.ComplimentFailureFactory
import io.realm.Realm
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ComplimentsApp : Application() {

    lateinit var baseViewModel : BaseViewModel

    override fun onCreate() {
        super.onCreate()
        Realm.init(this)

        val retrofit = Retrofit.Builder()
            .baseUrl("https://www.google.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val cachedCompliment = BaseCachedCompliment()
        val cacheDataSource = BaseCacheDataSource(BaseRealmProvider(), ComplimentRealmMapper())
        val resourceManager = BaseResourceManager(this)
        val cloudDataSource = BaseCloudDataSource(retrofit.create(ComplimentService::class.java))

        val repository = BaseRepository(cacheDataSource, cloudDataSource, cachedCompliment)
        val interactor = BaseComplimentInteractor(repository, ComplimentFailureFactory(resourceManager),
            ComplimentSuccessMapper())

        baseViewModel = BaseViewModel(interactor, BaseCommunication())
    }
}