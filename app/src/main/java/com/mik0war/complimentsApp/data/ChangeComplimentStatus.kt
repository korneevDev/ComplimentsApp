package com.mik0war.complimentsApp.data

interface ChangeComplimentStatus {
    suspend fun addOrRemove(id : String, compliment: ComplimentDataModel) : ComplimentDataModel
}