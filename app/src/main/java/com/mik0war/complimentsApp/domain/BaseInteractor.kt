package com.mik0war.complimentsApp.domain

import com.mik0war.complimentsApp.data.CommonDataModelMapper
import com.mik0war.complimentsApp.data.Repository

interface CommonInteractor {
    suspend fun getItem() : CommonItem
    suspend fun changeFavorites() : CommonItem
    fun getFavoriteItems(flag: Boolean)
}

class BaseInteractor(
    private val repository: Repository,
    private val failureHandler: FailureHandler,
    private val mapper: CommonDataModelMapper<CommonItem.Success>
) : CommonInteractor {
    override suspend fun getItem(): CommonItem =
        try {
            repository.getCommonItem().map(mapper)
        } catch (e: Exception) {
            CommonItem.Failed(failureHandler.handle(e))
        }

    override suspend fun changeFavorites(): CommonItem {
        return try {
            repository.changeStatus().map(mapper)
        } catch (e: Exception) {
            CommonItem.Failed(failureHandler.handle(e))
        }
    }

    override fun getFavoriteItems(flag: Boolean) {
        repository.chooseDataSource(flag)
    }
}