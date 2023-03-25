package com.mik0war.complimentsApp

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import com.mik0war.complimentsApp.data.cache.BaseRealmProvider
import com.mik0war.complimentsApp.domain.FailureFactory
import com.mik0war.complimentsApp.presentation.BaseResourceManager
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ComplimentsApp : Application() {
    private val useMocks = false
    private val factory by lazy {
        ViewModelsFactory(
            ComplimentModule(coreModule, useMocks),
            QuoteModule(coreModule, useMocks)
        )
    }

    private lateinit var coreModule: CommonInstancesProvider

    override fun onCreate() {
        super.onCreate()

        val failureHandler = FailureFactory(BaseResourceManager(this))
        val realmProvider = BaseRealmProvider(this, useMocks)

        val retrofit = Retrofit.Builder()
            .baseUrl("https://www.google.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        coreModule = CommonInstancesProvider.Base(this, failureHandler, realmProvider, retrofit)
    }

    fun <T: ViewModel> get(modelClass: Class<T>, owner: ViewModelStoreOwner) : T =
        ViewModelProvider(owner, factory)[modelClass]
}