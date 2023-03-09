package com.mik0war.complimentsApp.data

class ComplimentDataModel(
    val compliment: String,
    val cached: Boolean = false
) : ChangeCompliment {
    override suspend fun change(cacheDataSource: ChangeComplimentStatus): ComplimentDataModel {
        return cacheDataSource.addOrRemove(compliment, this)
    }

    fun toRealm() = ComplimentRealm().also {
        it.id = compliment
        it.text = compliment
    }

    fun changeCached(cached : Boolean) : ComplimentDataModel{
        return ComplimentDataModel(compliment, cached)
    }
}