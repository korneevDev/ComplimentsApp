package com.mik0war.complimentsApp.data.cache

import com.mik0war.complimentsApp.core.data.CommonDataModelMapper
import com.mik0war.complimentsApp.core.data.cache.RealmProvider
import com.mik0war.complimentsApp.core.data.cache.RealmToCommonDataMapper
import com.mik0war.complimentsApp.core.data.cache.CacheDataSource
import com.mik0war.complimentsApp.core.domain.NoFavorites
import com.mik0war.complimentsApp.data.CommonDataModel
import io.realm.RealmObject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

abstract class BaseCacheDataSource<T : RealmObject>(
    private val realmProvider: RealmProvider,
    private val mapper: CommonDataModelMapper<T>,
    private val commonDataMapper: RealmToCommonDataMapper<T>
) : CacheDataSource {
    protected abstract val dbClass : Class<T>
    override suspend fun getData(): CommonDataModel {
        realmProvider.provide().let{
            val compliments = it.where(dbClass).findAll()
            if(compliments.isEmpty())
                throw NoFavorites()
            else
                return commonDataMapper.map(compliments.random())
        }
    }
    override suspend fun addOrRemove(id: String, model: CommonDataModel): CommonDataModel =
        withContext(Dispatchers.IO) {
            realmProvider.provide().use {
                val curCompliment =
                    it.where(dbClass).equalTo("id", id).findFirst()

                return@withContext if (curCompliment == null) {
                    it.executeTransaction { transaction ->
                        transaction.insert(model.map(mapper))
                    }
                    model.changeCached(true)
                } else {
                    it.executeTransaction {
                        curCompliment.deleteFromRealm()
                    }
                    model.changeCached(false)
                }
            }
        }
}