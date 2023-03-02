package com.mik0war.complimentsApp

import io.realm.Realm
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class BaseCacheDataSource(private val realmProvider: RealmProvider) : CacheDataSource {
    override suspend fun getCompliment(): Result<Compliment, Unit> {
        realmProvider.provide().let{
            val compliments = it.where(ComplimentRealm::class.java).findAll()
            if(compliments.isEmpty())
                return Result.Error(Unit)
            else
                return Result.Success(Compliment(
                    compliment= compliments.random().text)
                )
        }
    }

    override suspend fun addOrRemove(id: String, compliment: Compliment): ComplimentUIModel =
        withContext(Dispatchers.IO) {
            Realm.getDefaultInstance().use {
                val curCompliment =
                    realmProvider.provide().where(ComplimentRealm::class.java).equalTo("id", id).findFirst()

                return@withContext if (curCompliment == null) {
                    it.executeTransaction { transaction ->
                        transaction.insert(compliment.toRealmModel())
                    }
                    compliment.toFavoriteCompliment()
                } else {
                    it.executeTransaction {
                        curCompliment.deleteFromRealm()
                    }

                    compliment.toBaseCompliment()
                }
            }
        }
}