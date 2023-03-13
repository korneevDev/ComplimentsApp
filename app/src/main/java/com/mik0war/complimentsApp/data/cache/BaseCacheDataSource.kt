package com.mik0war.complimentsApp.data.cache

import com.mik0war.complimentsApp.core.data.CommonDataModelMapper
import com.mik0war.complimentsApp.core.data.cache.RealmProvider
import com.mik0war.complimentsApp.core.data.cache.RealmToCommonDataMapper
import com.mik0war.complimentsApp.core.data.cache.CacheDataSource
import com.mik0war.complimentsApp.core.domain.NoFavorites
import com.mik0war.complimentsApp.data.CommonDataModel
import io.realm.RealmObject
import io.realm.RealmResults
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

abstract class BaseCacheDataSource<T : RealmObject>(
    private val realmProvider: RealmProvider,
    private val mapper: CommonDataModelMapper<T>,
    private val commonDataMapper: RealmToCommonDataMapper<T>
) : CacheDataSource {
    protected abstract val dbClass : Class<T>


    override suspend fun getDataList() = getRealmData{
            list -> list.map{ commonDataMapper.map(it)}
    }
    override suspend fun getData() = getRealmData{
        commonDataMapper.map(it.random())
    }

    private fun <E> getRealmData(block: (list: RealmResults<T>) -> E): E {
        realmProvider.provide().let{
            val list = it.where(dbClass).findAll()
            if(list.isEmpty())
                throw NoFavorites()
            else
                return block.invoke(list)
        }
    }
    override suspend fun addOrRemove(id: String, model: CommonDataModel): CommonDataModel =
        withContext(Dispatchers.IO) {
            realmProvider.provide().use {
                val curItem =
                    it.where(dbClass).equalTo("id", id).findFirst()

                return@withContext if (curItem == null) {
                    it.executeTransaction { transaction ->
                        transaction.insert(model.map(mapper))
                    }
                    model.changeCached(true)
                } else {
                    it.executeTransaction {
                        curItem.deleteFromRealm()
                    }
                    model.changeCached(false)
                }
            }
        }

    override suspend fun removeItem(id: String) = withContext(Dispatchers.IO) {
        realmProvider.provide().use {
            it.executeTransaction{transaction ->
                transaction.where(dbClass).equalTo("id", id).findFirst()?.deleteFromRealm()
            }
        }
    }
}