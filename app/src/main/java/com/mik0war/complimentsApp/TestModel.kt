package com.mik0war.complimentsApp

class TestModel(private val resourceManager: ResourceManager) : Model {
    private var count = 1
    private var callBack : ResultCallBack? = null

    override fun getCompliment() {
        Thread{
            Thread.sleep(5000)
            when {
                count % 3 == 0 -> callBack?.provideSuccess(Compliment("complimentBody"))
                count % 3 == 1 -> callBack?.provideError(NoConnection(resourceManager))
                else -> callBack?.provideError(ServiceUnavailable(resourceManager))
            }
            count++
        }.start()
    }

    override fun clear() {
        this.callBack = null
    }

    override fun init(callBack: ResultCallBack) {
        this.callBack = callBack
    }

}