package com.mik0war.complimentsApp

import com.mik0war.complimentsApp.data.BaseRepository
import com.mik0war.complimentsApp.data.CommonSuccessMapper
import com.mik0war.complimentsApp.data.cache.*
import com.mik0war.complimentsApp.data.cloud.ComplimentCloudDataSource
import com.mik0war.complimentsApp.data.cloud.ComplimentService
import com.mik0war.complimentsApp.data.mapper.ComplimentRealmMapper
import com.mik0war.complimentsApp.domain.BaseInteractor
import com.mik0war.complimentsApp.presentation.ComplimentViewMode

class ComplimentModule(private val coreModule: CommonInstancesProvider) : BaseModule<ComplimentViewMode>() {
    override fun getViewModel(): ComplimentViewMode {
        return ComplimentViewMode(
            BaseInteractor(
                BaseRepository(
                    ComplimentCacheDataSource(
                        coreModule.provideRealmProvider(),
                        ComplimentRealmMapper(),
                        ComplimentRealmToCommonDataMapper()
                    ),
                    ComplimentCloudDataSource(
                        coreModule.retrofitCreate(ComplimentService::class.java)
                    ),
                    BasePersistentDataSource(
                        BaseSharedPreferencesProvider(coreModule.provideContext())
                    ),
                    BaseCachedCommonItem()
                ),
                coreModule.provideFailureHandler(),
                CommonSuccessMapper()
            ),
            getCommunication())
    }
}