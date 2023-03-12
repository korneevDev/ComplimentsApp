package com.mik0war.complimentsApp.core.data

interface CommonDataModelMapper<T> {
    fun map(id: String, text: String, isCached: Boolean) : T
}