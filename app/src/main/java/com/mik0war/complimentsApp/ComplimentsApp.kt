package com.mik0war.complimentsApp

import android.app.Application
import com.mik0war.complimentsApp.core.presentation.CommonCommunication

import com.mik0war.complimentsApp.data.BaseRepository
import com.mik0war.complimentsApp.data.CommonSuccessMapper
import com.mik0war.complimentsApp.data.cache.*
import com.mik0war.complimentsApp.data.cloud.ComplimentCloudDataSource
import com.mik0war.complimentsApp.data.cloud.ComplimentService
import com.mik0war.complimentsApp.data.cloud.QuoteCloudDataSource
import com.mik0war.complimentsApp.data.cloud.QuoteService
import com.mik0war.complimentsApp.data.mapper.ComplimentRealmMapper
import com.mik0war.complimentsApp.data.mapper.QuoteRealmMapper
import com.mik0war.complimentsApp.domain.BaseInteractor
import com.mik0war.complimentsApp.domain.FailureFactory
import com.mik0war.complimentsApp.presentation.BaseCommunication
import com.mik0war.complimentsApp.presentation.BaseResourceManager
import com.mik0war.complimentsApp.presentation.BaseViewModel
import io.realm.Realm
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ComplimentsApp : Application() {
    lateinit var quoteViewModel : BaseViewModel
    lateinit var quoteCommunication : CommonCommunication

    lateinit var complimentViewModel : BaseViewModel
    lateinit var complimentCommunication : CommonCommunication

    override fun onCreate() {
        super.onCreate()
        Realm.init(this)

        val retrofit = Retrofit.Builder()
            .baseUrl("https://www.google.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val realmProvider = BaseRealmProvider()
        val failureFactory = FailureFactory(BaseResourceManager(this))

        complimentCommunication = BaseCommunication()
        complimentViewModel = BaseViewModel(
            BaseInteractor(
                BaseRepository(
                    ComplimentCacheDataSource(
                        realmProvider,
                        ComplimentRealmMapper(),
                        ComplimentRealmToCommonDataMapper()
                    ),
                    ComplimentCloudDataSource(retrofit.create(ComplimentService::class.java)),
                    BaseCachedCommonItem()
                ), failureFactory, CommonSuccessMapper()
            ),
            complimentCommunication
        )

        quoteCommunication = BaseCommunication()
        quoteViewModel = BaseViewModel(
            BaseInteractor(
                BaseRepository(
                    QuoteCacheDataSource(
                        realmProvider,
                        QuoteRealmMapper(),
                        QuoteRealmToCommonDataMapper()
                    ),
                    QuoteCloudDataSource(retrofit.create(QuoteService::class.java)),
                    BaseCachedCommonItem()
                ), failureFactory, CommonSuccessMapper()
        )
        , quoteCommunication
        )
    }
}