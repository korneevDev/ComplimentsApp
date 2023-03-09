package com.mik0war.complimentsApp.data

interface ChangeCompliment {
    suspend fun change(cacheDataSource: ChangeComplimentStatus) : ComplimentDataModel

    class Empty : ChangeCompliment {
        override suspend fun change(cacheDataSource: ChangeComplimentStatus): ComplimentDataModel {
            throw IllegalStateException("empty change compliment called")
        }
    }
}