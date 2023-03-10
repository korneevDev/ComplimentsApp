package com.mik0war.complimentsApp.domain

import com.mik0war.complimentsApp.data.ComplimentDataModelMapper
import com.mik0war.complimentsApp.data.Repository

interface ComplimentInteractor {
    suspend fun getCompliment() : Compliment
    suspend fun changeFavorites() : Compliment
    fun getFavoriteCompliments(flag: Boolean)
}

class BaseComplimentInteractor(
    private val repository: Repository,
    private val complimentFailureHandler: ComplimentFailureHandler,
    private val mapper: ComplimentDataModelMapper<Compliment.Success>
) : ComplimentInteractor {
    override suspend fun getCompliment(): Compliment =
        try {
            repository.getCompliment().map(mapper)
        } catch (e: Exception) {
            Compliment.Failed(complimentFailureHandler.handle(e))
        }

    override suspend fun changeFavorites(): Compliment {
        return try {
            repository.changeComplimentStatus().map(mapper)
        } catch (e: Exception) {
            Compliment.Failed(complimentFailureHandler.handle(e))
        }
    }

    override fun getFavoriteCompliments(flag: Boolean) {
        repository.chooseDataSource(flag)
    }
}