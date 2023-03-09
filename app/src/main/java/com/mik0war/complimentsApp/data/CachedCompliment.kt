package com.mik0war.complimentsApp.data

interface CachedCompliment : ChangeCompliment {
    fun save(compliment: ComplimentDataModel)
    fun clear()
}

class BaseCachedCompliment : CachedCompliment {
    private var cached : ChangeCompliment = ChangeCompliment.Empty()

    override fun save(compliment: ComplimentDataModel) {
        cached = compliment
    }

    override fun clear() {
        cached = ChangeCompliment.Empty()
    }

    override suspend fun change(cacheDataSource: ChangeComplimentStatus): ComplimentDataModel {
        return cached.change(cacheDataSource)
    }

}