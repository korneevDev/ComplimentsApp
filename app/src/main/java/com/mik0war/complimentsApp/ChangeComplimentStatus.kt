package com.mik0war.complimentsApp

interface ChangeComplimentStatus {
    suspend fun addOrRemove(id : String, compliment: Compliment) : ComplimentUIModel
}