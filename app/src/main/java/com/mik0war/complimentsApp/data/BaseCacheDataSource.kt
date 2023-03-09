package com.mik0war.complimentsApp.data

import com.mik0war.complimentsApp.domain.NoFavoriteComplimentsException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class BaseCacheDataSource(
    private val realmProvider: RealmProvider,
    private val mapper: ComplimentDataModelMapper<ComplimentRealm>
) : CacheDataSource {
    override suspend fun getCompliment(): ComplimentDataModel {
        realmProvider.provide().let{
            val compliments = it.where(ComplimentRealm::class.java).findAll()
            if(compliments.isEmpty())
                throw NoFavoriteComplimentsException()
            else
                return compliments.random().to()
        }
    }

    override suspend fun addOrRemove(id: String, compliment: ComplimentDataModel): ComplimentDataModel =
        withContext(Dispatchers.IO) {
            realmProvider.provide().use {
                val curCompliment =
                    it.where(ComplimentRealm::class.java).equalTo("id", id).findFirst()

                return@withContext if (curCompliment == null) {
                    it.executeTransaction { transaction ->
                        transaction.insert(compliment.map(mapper))
                    }
                    compliment.changeCached(true)
                } else {
                    it.executeTransaction {
                        curCompliment.deleteFromRealm()
                    }
                    compliment.changeCached(false)
                }
            }
        }
}