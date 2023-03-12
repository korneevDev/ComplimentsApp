package com.mik0war.complimentsApp.data

import com.mik0war.complimentsApp.domain.CommonItem

interface CommonDataModelMapper<T> {
    fun map(id: String, text: String, isCached: Boolean) : T
}

class CommonSuccessMapper : CommonDataModelMapper<CommonItem.Success>{
    override fun map(id: String, text: String, isCached: Boolean) =
        CommonItem.Success(text, isCached)
}

class ComplimentRealmMapper : CommonDataModelMapper<ComplimentRealm>{
    override fun map(id: String, text: String, isCached: Boolean) =
        ComplimentRealm().also {
            it.id = id
            it.text = text
        }
}

class QuoteRealmMapper : CommonDataModelMapper<QuoteRealm>{
    override fun map(id: String, text: String, isCached: Boolean): QuoteRealm =
        QuoteRealm().also {
            it.id = id
            it.text = text
        }
}