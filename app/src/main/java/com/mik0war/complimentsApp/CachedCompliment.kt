package com.mik0war.complimentsApp

interface CachedCompliment : ChangeCompliment{
    fun save(compliment: Compliment)
    fun clear()
}

class BaseCachedCompliment : CachedCompliment{
    private var cached : Compliment? = null

    override fun save(compliment: Compliment) {
        cached = compliment
    }

    override fun clear() {
        cached = null
    }

    override suspend fun change(cacheDataSource: ChangeComplimentStatus): ComplimentUIModel? {
        return cached?.change(cacheDataSource)
    }

}