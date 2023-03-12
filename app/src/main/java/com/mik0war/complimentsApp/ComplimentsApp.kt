package com.mik0war.complimentsApp

import android.app.Application
import com.mik0war.complimentsApp.base.data.cloud.ComplimentService
import com.mik0war.complimentsApp.base.data.cache.BaseCachedCommonItem
import com.mik0war.complimentsApp.base.data.mapper.ComplimentRealmMapper
import com.mik0war.complimentsApp.base.data.cache.BaseRealmProvider
import com.mik0war.complimentsApp.base.data.BaseRepository
import com.mik0war.complimentsApp.base.data.CommonSuccessMapper
import com.mik0war.complimentsApp.base.domain.BaseInteractor
import com.mik0war.complimentsApp.base.domain.FailureFactory
import com.mik0war.complimentsApp.base.presentation.BaseCommunication
import com.mik0war.complimentsApp.base.presentation.BaseResourceManager
import com.mik0war.complimentsApp.base.presentation.BaseViewModel
import com.mik0war.complimentsApp.base.data.cache.ComplimentCacheDataSource
import com.mik0war.complimentsApp.base.data.cloud.ComplimentCloudDataSource
import com.mik0war.complimentsApp.base.data.cache.ComplimentRealmToCommonDataMapper
import com.mik0war.complimentsApp.base.data.cache.QuoteCacheDataSource
import com.mik0war.complimentsApp.base.data.cloud.QuoteCloudDataSource
import com.mik0war.complimentsApp.base.data.cloud.QuoteService
import com.mik0war.complimentsApp.base.data.mapper.QuoteRealmMapper
import com.mik0war.complimentsApp.base.data.cache.QuoteRealmToCommonDataMapper
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
            ComplimentRealmMapper(), ComplimentRealmToCommonDataMapper()
        )
        val complimentCloudDataSource = ComplimentCloudDataSource(retrofit.create(ComplimentService::class.java))

        val repository = BaseRepository(complimentCacheDataSource, complimentCloudDataSource, BaseCachedCommonItem())
        val interactor = BaseInteractor(repository, failureFactory,
            CommonSuccessMapper()
        )

        baseViewModel = BaseViewModel(interactor, BaseCommunication())

        quoteViewModel = BaseViewModel(
            BaseInteractor(
            BaseRepository(
            QuoteCacheDataSource(realmProvider, QuoteRealmMapper(), QuoteRealmToCommonDataMapper()),
            QuoteCloudDataSource(retrofit.create(QuoteService::class.java)),
            BaseCachedCommonItem()
        ), failureFactory, CommonSuccessMapper()
        )
        , BaseCommunication()
        )
    }
}