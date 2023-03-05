package com.mik0war.complimentsApp

import kotlinx.coroutines.runBlocking
import org.junit.Test

class BaseModelUnitTest {

    @Test
    fun test_change_data_source(): Unit = runBlocking {
//        val cacheDataSource = TestCacheDataSource()
//        val cloudDataSource = TestCloudDatasource()
//        val resourceManager = TestResourceManager()
//
//        val model = BaseModel(cloudDataSource, cacheDataSource, resourceManager)
//        model.chooseDataSource(false)
//
//        cloudDataSource.getComplimentWithResult(true)
//        val compliment = model.getCompliment()
//
//        assert(compliment is BaseComplimentUIModel)
//        model.changeComplimentStatus()
//
//        assert(cacheDataSource.checkContainsId("text0"))
    }

    private inner class TestCacheDataSource : CacheDataSource {

        private val map = HashMap<String, Compliment>()
        private var success = true
        private var nextComplimentId = ""

        fun getNextComplimentWithResult(success: Boolean, id: String) {
            this.success = success
            nextComplimentId = id
        }

        fun checkContainsId(id: String) = map.containsKey(id)

        override suspend fun getCompliment(): Result<Compliment, Unit> {
            return if (success)
                Result.Success(map[nextComplimentId]!!)
            else
                Result.Error(Unit)
        }

        override suspend fun addOrRemove(id: String, compliment: Compliment): ComplimentUIModel {
            return if (map.containsKey(id)) {
                map.remove(id)!!.toBaseCompliment()
            } else {
                map[id] = compliment
                compliment.toFavoriteCompliment()
            }
        }
    }

    private inner class TestCloudDatasource : CloudDataSource {
        private var success = true
        private var count = 0

        fun getComplimentWithResult(success: Boolean) {
            this.success = success
        }

        override suspend fun getCompliment(): Result<ComplimentServerModel, ErrorType> {
            return if (success)
                Result.Success(ComplimentServerModel("text$count"))
            else
                Result.Error(ErrorType.NO_CONNECTION)

        }
    }

    private inner class TestResourceManager : ResourceManager {

        private val message = ""
        override fun getString(resId: Int) = message

    }

}