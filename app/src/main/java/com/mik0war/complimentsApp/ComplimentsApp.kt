package com.mik0war.complimentsApp

import android.app.Application
import com.mik0war.complimentsApp.core.domain.FailureHandler
import com.mik0war.complimentsApp.data.cache.BaseRealmProvider
import com.mik0war.complimentsApp.domain.FailureFactory
import com.mik0war.complimentsApp.presentation.BaseResourceManager
import io.realm.Realm
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ComplimentsApp : Application() {
    val factory by lazy {
        ViewModelsFactory(
            ComplimentModule(failureHandler, realmProvider, retrofit),
            QuoteModule(failureHandler, realmProvider, retrofit)
        )
    }

    private lateinit var failureHandler: FailureHandler
    private lateinit var realmProvider: BaseRealmProvider
    private lateinit var retrofit: Retrofit

    override fun onCreate() {
        super.onCreate()
        Realm.init(this)

        retrofit = Retrofit.Builder()
            .baseUrl("https://www.google.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        realmProvider = BaseRealmProvider()
        failureHandler = FailureFactory(BaseResourceManager(this))
    }
}