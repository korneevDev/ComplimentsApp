package com.mik0war.complimentsApp.domain

import com.mik0war.complimentsApp.core.data.CommonDataModelMapper
import com.mik0war.complimentsApp.core.data.Repository
import com.mik0war.complimentsApp.core.domain.CommonInteractor
import com.mik0war.complimentsApp.core.domain.FailureHandler

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