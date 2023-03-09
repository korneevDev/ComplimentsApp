package com.mik0war.complimentsApp.data

import com.mik0war.complimentsApp.domain.Compliment

interface ComplimentDataModelMapper<T> {
    fun map(id: String, text: String, isCached: Boolean) : T
}

class ComplimentSuccessMapper : ComplimentDataModelMapper<Compliment.Success>{
    override fun map(id: String, text: String, isCached: Boolean) =
        Compliment.Success(text, isCached)
}

class ComplimentRealmMapper : ComplimentDataModelMapper<ComplimentRealm>{
    override fun map(id: String, text: String, isCached: Boolean) =
        ComplimentRealm().also {
            it.id = id
            it.text = text
        }
}