package com.mik0war.complimentsApp

import io.realm.Realm

class BaseCacheDataSource(private val realm: Realm) : CacheDataSource {
    override fun getCompliment(complimentCallBack: ComplimentCacheCallBack) {
        realm.let{
            val compliments = realm.where(ComplimentRealm::class.java).findAll()
            if(compliments.isEmpty())
                complimentCallBack.fail()
            else
                complimentCallBack.provide(Compliment(compliment= compliments.random().text))
        }
    }

    override fun addOrRemove(id: String, compliment: Compliment): ComplimentUIModel {
        realm.let {
            val curCompliment = realm.where(ComplimentRealm::class.java).equalTo("id", id).findFirst()

            return if (curCompliment == null){
                it.executeTransactionAsync{transaction ->
                    transaction.insert(compliment.toRealmModel())
                }
                compliment.toFavoriteCompliment()
            } else {
                it.executeTransaction{
                    curCompliment.deleteFromRealm()
                }

                compliment.toBaseCompliment()
            }
        }
    }
}