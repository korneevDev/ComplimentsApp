package com.mik0war.complimentsApp

import com.mik0war.complimentsApp.data.BaseRepository
import com.mik0war.complimentsApp.data.CommonSuccessMapper
import com.mik0war.complimentsApp.data.cache.*
import com.mik0war.complimentsApp.data.cloud.MockQuoteCloudDataSource
import com.mik0war.complimentsApp.data.cloud.QuoteCloudDataSource
import com.mik0war.complimentsApp.data.cloud.QuoteService
import com.mik0war.complimentsApp.data.mapper.QuoteRealmMapper
import com.mik0war.complimentsApp.domain.BaseInteractor
import com.mik0war.complimentsApp.presentation.QuoteViewMode

class QuoteModule(
    private val coreModule: CommonInstancesProvider,
    private val useMocks: Boolean
) : BaseModule<QuoteViewMode>() {
    override fun getViewModel(): QuoteViewMode {
        return QuoteViewMode(
            BaseInteractor(
                BaseRepository(
                    QuoteCacheDataSource(
                        coreModule.provideRealmProvider(),
                        QuoteRealmMapper(),
                        QuoteRealmToCommonDataMapper()
                    ),
                    if (useMocks)
                        MockQuoteCloudDataSource()
                    else
                        QuoteCloudDataSource(
                            coreModule.retrofitCreate(QuoteService::class.java)
                        ),
                    BasePersistentDataSource(
                        BaseSharedPreferencesProvider(coreModule.provideContext())
                    ),
                    BaseCachedCommonItem()
                ),
                coreModule.provideFailureHandler(),
                CommonSuccessMapper()
            ),
            getCommunication()
        )
    }
}