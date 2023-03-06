package com.mik0war.complimentsApp

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import org.junit.Assert.*
import org.junit.Test

class BaseViewModelTest {
    private val model = TestModel()
    private val communication = TestCommunication()
    @OptIn(ExperimentalCoroutinesApi::class)
    private val viewModelTest = BaseViewModel(model, communication, UnconfinedTestDispatcher())

    @ExperimentalCoroutinesApi
    @Test
    fun test_get_compliment_from_cloud_success(){
        model.success = true
        viewModelTest.changeDataSource(false)
        viewModelTest.getCompliment()

        val actualText = communication.text
        val actualId = communication.id
        val expectedText = "cloud base compliment"
        val expectedId = 0

        assertEquals(expectedText, actualText)
        assertNotEquals(expectedId, actualId)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun test_get_compliment_from_cloud_fail(){
        model.success = false
        viewModelTest.changeDataSource(false)
        viewModelTest.getCompliment()

        val actualText = communication.text
        val actualId = communication.id
        val expectedText = "cloud fail"
        val expectedId = 0

        assertEquals(expectedText, actualText)
        assertEquals(expectedId, actualId)
    }

    private inner class TestModel : Model {
        private val cachedComplimentUIModel = BaseComplimentUIModel("cached base compliment")
        private val cachedFailedComplimentUIModel = FailedComplimentUIModel("cached fail")
        private val cloudComplimentUIModel = BaseComplimentUIModel("cloud base compliment")
        private val cloudFailedComplimentUIModel = FailedComplimentUIModel("cloud fail")

        private var currentCompliment : ComplimentUIModel? = cloudComplimentUIModel
        private var isGetFromCache = false
        var success = false

        override suspend fun getCompliment(): ComplimentUIModel {
            return if(success){
                if(isGetFromCache)
                    cachedComplimentUIModel.also {
                        currentCompliment = it
                    }
                else
                    cloudComplimentUIModel.also {
                        currentCompliment = it
                    }
            }
            else{
                currentCompliment = null
                if(isGetFromCache)
                    cachedFailedComplimentUIModel
                else
                    cloudFailedComplimentUIModel
            }
        }

        override suspend fun changeComplimentStatus(): ComplimentUIModel? {
            return currentCompliment
        }

        override fun chooseDataSource(isCache: Boolean) {
            isGetFromCache = isCache
        }
    }

    private inner class TestCommunication : Communication {
        var text = ""
        var id = -1
        var observedCount = 0

        override fun showData(data: Pair<String, Int>) {
            text = data.first
            id = data.second
        }

        override fun observe(owner: LifecycleOwner, observer: Observer<Pair<String, Int>>) {
            observedCount++
        }
    }
}