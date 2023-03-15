package com.mik0war.complimentsApp

import com.mik0war.complimentsApp.core.domain.FailureHandler
import com.mik0war.complimentsApp.data.BaseRepository
import com.mik0war.complimentsApp.data.CommonSuccessMapper
import com.mik0war.complimentsApp.data.cache.BaseCachedCommonItem
import com.mik0war.complimentsApp.data.cache.BaseRealmProvider
import com.mik0war.complimentsApp.data.cache.QuoteCacheDataSource
import com.mik0war.complimentsApp.data.cache.QuoteRealmToCommonDataMapper
import com.mik0war.complimentsApp.data.cloud.QuoteCloudDataSource
import com.mik0war.complimentsApp.data.cloud.QuoteService
import com.mik0war.complimentsApp.data.mapper.QuoteRealmMapper
import com.mik0war.complimentsApp.domain.BaseInteractor
import com.mik0war.complimentsApp.presentation.BaseCommunication
import com.mik0war.complimentsApp.presentation.QuoteViewMode
import retrofit2.Retrofit

class QuoteModule (
    private val failureHandler: FailureHandler,
    private val realmProvider: BaseRealmProvider,
    private val retrofit: Retrofit
) : BaseModule<QuoteViewMode>(){
    private var communication : BaseCommunication? = null

    override fun getViewModel(): QuoteViewMode {
        return QuoteViewMode(getInteractor(), getCommunication())
    }

    private fun getInteractor() =
        BaseInteractor(getRepository(), failureHandler, CommonSuccessMapper())

    private fun getRepository() =
        BaseRepository(getCloudDataSource(), getCacheDataSource(), BaseCachedCommonItem())

    private fun getCloudDataSource() =
        QuoteCacheDataSource(realmProvider, QuoteRealmMapper(), QuoteRealmToCommonDataMapper())

    private fun getCacheDataSource() = QuoteCloudDataSource(retrofit.create(QuoteService::class.java))

    override fun getCommunication(): BaseCommunication =
        communication ?: BaseCommunication().also { communication = it }
}