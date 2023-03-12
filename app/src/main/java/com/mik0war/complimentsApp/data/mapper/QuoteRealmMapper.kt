package com.mik0war.complimentsApp.data.mapper

import com.mik0war.complimentsApp.core.data.CommonDataModelMapper
import com.mik0war.complimentsApp.data.cache.QuoteRealm

class QuoteRealmMapper : CommonDataModelMapper<QuoteRealm> {
    override fun map(id: String, text: String, isCached: Boolean): QuoteRealm =
        QuoteRealm().also {
            it.id = id
            it.text = text
        }
}