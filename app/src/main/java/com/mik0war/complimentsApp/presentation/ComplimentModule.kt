package com.mik0war.complimentsApp.presentation

import com.mik0war.complimentsApp.core.domain.FailureHandler
import com.mik0war.complimentsApp.data.BaseRepository
import com.mik0war.complimentsApp.data.CommonSuccessMapper
import com.mik0war.complimentsApp.data.cache.BaseCachedCommonItem
import com.mik0war.complimentsApp.data.cache.BaseRealmProvider
import com.mik0war.complimentsApp.data.cache.ComplimentCacheDataSource
import com.mik0war.complimentsApp.data.cache.ComplimentRealmToCommonDataMapper
import com.mik0war.complimentsApp.data.cloud.ComplimentCloudDataSource
import com.mik0war.complimentsApp.data.cloud.ComplimentService
import com.mik0war.complimentsApp.data.mapper.ComplimentRealmMapper
import com.mik0war.complimentsApp.domain.BaseInteractor
import retrofit2.Retrofit

class ComplimentModule(
    private val failureHandler: FailureHandler,
    private val realmProvider: BaseRealmProvider,
    private val retrofit: Retrofit
) : BaseModule<ComplimentViewMode>(){
    private var communication : BaseCommunication? = null

    override fun getViewModel(): ComplimentViewMode {
        return ComplimentViewMode(getInteractor(), getCommunication())
    }

    private fun getInteractor() =
        BaseInteractor(getRepository(), failureHandler, CommonSuccessMapper())

    private fun getRepository() =
        BaseRepository(getCloudDataSource(), getCacheDataSource(), BaseCachedCommonItem())

    private fun getCloudDataSource() =
        ComplimentCacheDataSource(realmProvider, ComplimentRealmMapper(), ComplimentRealmToCommonDataMapper())

    private fun getCacheDataSource() = ComplimentCloudDataSource(retrofit.create(ComplimentService::class.java))

    override fun getCommunication(): BaseCommunication =
        communication ?: BaseCommunication().also { communication = it }
}