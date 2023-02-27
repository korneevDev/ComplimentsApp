package com.mik0war.complimentsApp

class TestModel() : Model {
    private var count = 1
    private var callBack : ResultCallBack? = null

    override fun getCompliment() {
        Thread{
            Thread.sleep(5000)
            when {
                count % 3 == 0 -> callBack?.provideCompliment(BaseCompliment("base compliment"))
                count % 3 == 1 -> callBack?.provideCompliment(FavoriteCompliment("favorite compliment"))
                else -> callBack?.provideCompliment(FailedCompliment("failed compliment"))
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