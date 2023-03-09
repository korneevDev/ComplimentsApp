package com.mik0war.complimentsApp.data

class ComplimentDataModel(
    private val compliment: String,
    private val cached: Boolean = false
) : ChangeCompliment {
    override suspend fun change(cacheDataSource: ChangeComplimentStatus): ComplimentDataModel {
        return cacheDataSource.addOrRemove(compliment, this)
    }

    fun <T> map(mapper : ComplimentDataModelMapper<T>) : T{
        return mapper.map(compliment, compliment, cached)
    }

    fun changeCached(cached : Boolean) : ComplimentDataModel{
        return ComplimentDataModel(compliment, cached)
    }
}