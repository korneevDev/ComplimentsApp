package com.mik0war.complimentsApp.presentation

import android.app.Application
import com.mik0war.complimentsApp.data.*
import com.mik0war.complimentsApp.domain.*
import io.realm.Realm
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ComplimentsApp : Application() {

    lateinit var baseViewModel : BaseViewModel
    lateinit var quoteViewModel: BaseViewModel

    override fun onCreate() {
        super.onCreate()
        Realm.init(this)

        val retrofit = Retrofit.Builder()
            .baseUrl("https://www.google.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val realmProvider = BaseRealmProvider()
        val failureFactory = FailureFactory(BaseResourceManager(this))
        val complimentCacheDataSource = ComplimentCacheDataSource(realmProvider,
            ComplimentRealmMapper(), ComplimentRealmToCommonDataMapper())
        val complimentCloudDataSource = ComplimentCloudDataSource(retrofit.create(ComplimentService::class.java))

        val repository = BaseRepository(complimentCacheDataSource, complimentCloudDataSource, BaseCachedCommonItem())
        val interactor = BaseInteractor(repository, failureFactory,
            CommonSuccessMapper())

        baseViewModel = BaseViewModel(interactor, BaseCommunication())

        quoteViewModel = BaseViewModel(BaseInteractor(BaseRepository(
            QuoteCacheDataSource(realmProvider, QuoteRealmMapper(), QuoteRealmToCommonDataMapper()),
            QuoteCloudDataSource(retrofit.create(QuoteService::class.java)),
            BaseCachedCommonItem()
        ), failureFactory, CommonSuccessMapper())
        , BaseCommunication())
    }
}