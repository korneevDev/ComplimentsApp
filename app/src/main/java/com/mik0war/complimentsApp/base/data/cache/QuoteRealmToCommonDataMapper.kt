package com.mik0war.complimentsApp.base.data.cache

import com.mik0war.complimentsApp.core.data.cache.RealmToCommonDataMapper
import com.mik0war.complimentsApp.base.data.CommonDataModel

class QuoteRealmToCommonDataMapper : RealmToCommonDataMapper<QuoteRealm> {
    override fun map(realmObject: QuoteRealm): CommonDataModel =
        CommonDataModel(realmObject.id, realmObject.text, true)
}