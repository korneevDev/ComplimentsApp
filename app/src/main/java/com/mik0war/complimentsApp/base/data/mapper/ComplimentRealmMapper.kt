package com.mik0war.complimentsApp.base.data.mapper

import com.mik0war.complimentsApp.core.data.CommonDataModelMapper
import com.mik0war.complimentsApp.base.data.cache.ComplimentRealm

class ComplimentRealmMapper : CommonDataModelMapper<ComplimentRealm> {
    override fun map(id: String, text: String, isCached: Boolean) =
        ComplimentRealm().also {
            it.id = id
            it.text = text
        }
}