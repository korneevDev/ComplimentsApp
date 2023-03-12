package com.mik0war.complimentsApp.data

import com.mik0war.complimentsApp.core.Mapper
import com.mik0war.complimentsApp.domain.NoConnectionException
import com.mik0war.complimentsApp.domain.NoFavorites
import com.mik0war.complimentsApp.domain.ServiceUnavailableException
import io.realm.RealmObject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Call
import java.net.UnknownHostException

interface DataFetcher {
    suspend fun getData() : CommonDataModel
}

interface CloudDataSource : DataFetcher
abstract class BaseCloudDataSource<T : Mapper<CommonDataModel>> : CloudDataSource {
    protected abstract fun getServerModel() : Call<T>
    override suspend fun getData() : CommonDataModel {
        try {
            return getServerModel().execute().body()!!.to()
        } catch (e: Exception) {
            throw if (e is UnknownHostException)
                NoConnectionException()
            else
                ServiceUnavailableException()
        }
    }
}

class ComplimentCloudDataSource(private val service: ComplimentService) :
    BaseCloudDataSource<ComplimentServerModel>() {
    override fun getServerModel(): Call<ComplimentServerModel> = service.getCompliment()
}

class QuoteCloudDataSource(private val service: QuoteService) :
    BaseCloudDataSource<QuoteServerModel>() {
    override fun getServerModel(): Call<QuoteServerModel> = service.getQuote()
}

interface ChangeStatus {
    suspend fun addOrRemove(id : String, model: CommonDataModel) : CommonDataModel
}

interface CacheDataSource : DataFetcher, ChangeStatus
abstract class BaseCacheDataSource<T : RealmObject>(
    private val realmProvider: RealmProvider,
    private val mapper: CommonDataModelMapper<T>
) : CacheDataSource {
    protected abstract val dbClass : Class<T>
    override suspend fun getData(): CommonDataModel {
        realmProvider.provide().let{
            val compliments = it.where(dbClass).findAll()
            if(compliments.isEmpty())
                throw NoFavorites()
            else
                return (compliments.random() as Mapper<CommonDataModel>).to()
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

class ComplimentCacheDataSource( realmProvider: RealmProvider, mapper: ComplimentRealmMapper) :
    BaseCacheDataSource<ComplimentRealm>(realmProvider, mapper) {
    override val dbClass = ComplimentRealm::class.java
}

class QuoteCacheDataSource( realmProvider: RealmProvider, mapper: QuoteRealmMapper) :
    BaseCacheDataSource<QuoteRealm>(realmProvider, mapper) {
    override val dbClass = QuoteRealm::class.java
}