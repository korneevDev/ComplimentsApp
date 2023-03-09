package com.mik0war.complimentsApp.domain

import com.mik0war.complimentsApp.data.Repository

class BaseComplimentInteractor(
    private val repository: Repository,
    private val complimentFailureHandler: ComplimentFailureHandler
) : ComplimentInteractor {
    override suspend fun getCompliment(): Compliment =
        try {
            val compliment = repository.getCompliment()
            Compliment.Success(compliment.compliment, compliment.cached)
        } catch (e: Exception) {
            Compliment.Failed(complimentFailureHandler.handle(e))
        }

    override suspend fun changeFavorites(): Compliment {
        return try {
            val compliment = repository.changeComplimentStatus()
            Compliment.Success(compliment.compliment, compliment.cached)
        } catch (e: Exception) {
            Compliment.Failed(complimentFailureHandler.handle(e))
        }
    }

    override fun getFavoriteCompliments(flag: Boolean) {
        repository.chooseDataSource(flag)
    }
}