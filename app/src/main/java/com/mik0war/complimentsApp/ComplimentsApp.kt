package com.mik0war.complimentsApp

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import com.mik0war.complimentsApp.data.cache.BaseRealmProvider
import com.mik0war.complimentsApp.domain.FailureFactory
import com.mik0war.complimentsApp.presentation.BaseResourceManager
import io.realm.Realm
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ComplimentsApp : Application() {
    private val factory by lazy {
        ViewModelsFactory(
            ComplimentModule(coreModule),
            QuoteModule(coreModule)
        )
    }

    private lateinit var coreModule: CommonInstancesProvider

    override fun onCreate() {
        super.onCreate()

        val failureHandler = FailureFactory(BaseResourceManager(this))
        val realmProvider = BaseRealmProvider()

        Realm.init(this)
        val retrofit = Retrofit.Builder()
            .baseUrl("https://www.google.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        coreModule = CommonInstancesProvider.Base(failureHandler, realmProvider, retrofit)
    }

    fun <T: ViewModel> get(modelClass: Class<T>, owner: ViewModelStoreOwner) : T =
        ViewModelProvider(owner, factory)[modelClass]
}