package com.mik0war.complimentsApp

interface ChangeCompliment {
    suspend fun change(cacheDataSource: ChangeComplimentStatus) : ComplimentUIModel?
}