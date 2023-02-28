package com.mik0war.complimentsApp


class TestCacheDataSource : CacheDataSource {
    private val map = HashMap<String, ComplimentServerModel>()

    override fun addOrRemove(id : String, compliment: ComplimentServerModel): Compliment {
        return if(map.containsKey(id)){
            map.remove(id)
            compliment.toBaseCompliment()
        } else{
            map[id] = compliment
            compliment.toFavoriteCompliment()
        }
    }

    override fun getCompliment(complimentCallBack: ComplimentCacheCallBack) {
        if(map.isEmpty())
            complimentCallBack.fail()
        else
            complimentCallBack.provide(map.values.random())
    }
}