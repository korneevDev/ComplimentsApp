package com.mik0war.complimentsApp

class TestModel() : Model {
    private var count = 1
    private var callBack : ComplimentCallBack? = null

    override fun getCompliment() {
        Thread{
            Thread.sleep(5000)
            when {
                count % 3 == 0 -> callBack?.provideCompliment(BaseComplimentUIModel("base compliment"))
                count % 3 == 1 -> callBack?.provideCompliment(FavoriteComplimentUIModel("favorite compliment"))
                else -> callBack?.provideCompliment(FailedComplimentUIModel("failed compliment"))
            }
            count++
        }.start()
    }

    override fun clear() {
        this.callBack = null
    }

    override fun init(callBack: ComplimentCallBack) {
        this.callBack = callBack
    }

    override fun changeComplimentStatus(callBack: ComplimentCallBack) {
        TODO("Not yet implemented")
    }

    override fun chooseDataSource(isCache: Boolean) {
        TODO("Not yet implemented")
    }

}