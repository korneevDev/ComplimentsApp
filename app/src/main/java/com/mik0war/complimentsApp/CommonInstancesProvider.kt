package com.mik0war.complimentsApp

import android.content.Context
import com.mik0war.complimentsApp.core.domain.FailureHandler
import com.mik0war.complimentsApp.data.cache.BaseRealmProvider
import retrofit2.Retrofit


interface CommonInstancesProvider{
    fun <T> retrofitCreate(`class`: Class<T>): T
    fun provideFailureHandler(): FailureHandler
    fun provideRealmProvider(): BaseRealmProvider
    fun provideContext(): Context

    class Base(
        private val context: Context,
        private val failureHandler: FailureHandler,
        private val realmProvider: BaseRealmProvider,
        private val retrofit: Retrofit
    ) : CommonInstancesProvider{

        override fun <T> retrofitCreate(`class`: Class<T>): T = retrofit.create(`class`)
        override fun provideFailureHandler() = failureHandler
        override fun provideRealmProvider(): BaseRealmProvider = realmProvider
        override fun provideContext(): Context = context
    }

}