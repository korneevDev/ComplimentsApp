package com.mik0war.complimentsApp.data.cache

import com.mik0war.complimentsApp.core.data.cache.RealmToCommonDataMapper
import com.mik0war.complimentsApp.data.CommonDataModel

class QuoteRealmToCommonDataMapper : RealmToCommonDataMapper<QuoteRealm> {
    override fun map(realmObject: QuoteRealm): CommonDataModel =
        CommonDataModel(realmObject.id, realmObject.text, true)
}