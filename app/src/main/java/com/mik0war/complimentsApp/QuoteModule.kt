package com.mik0war.complimentsApp

import com.mik0war.complimentsApp.data.BaseRepository
import com.mik0war.complimentsApp.data.CommonSuccessMapper
import com.mik0war.complimentsApp.data.cache.BaseCachedCommonItem
import com.mik0war.complimentsApp.data.cache.QuoteCacheDataSource
import com.mik0war.complimentsApp.data.cache.QuoteRealmToCommonDataMapper
import com.mik0war.complimentsApp.data.cloud.QuoteCloudDataSource
import com.mik0war.complimentsApp.data.cloud.QuoteService
import com.mik0war.complimentsApp.data.mapper.QuoteRealmMapper
import com.mik0war.complimentsApp.domain.BaseInteractor
import com.mik0war.complimentsApp.presentation.QuoteViewMode

class QuoteModule (private val coreModule: CommonInstancesProvider) : BaseModule<QuoteViewMode>() {
    override fun getViewModel(): QuoteViewMode {
        return QuoteViewMode(
            BaseInteractor(
                BaseRepository(
                    QuoteCacheDataSource(
                        coreModule.provideRealmProvider(),
                        QuoteRealmMapper(),
                        QuoteRealmToCommonDataMapper()
                    ),
                    QuoteCloudDataSource(
                        coreModule.retrofitCreate(QuoteService::class.java)
                    ),
                    BaseCachedCommonItem()
                ),
                coreModule.provideFailureHandler(),
                CommonSuccessMapper()
            ),
            getCommunication())
    }
}