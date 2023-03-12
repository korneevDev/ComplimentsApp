package com.mik0war.complimentsApp.data.cache

import com.mik0war.complimentsApp.core.data.cache.RealmToCommonDataMapper
import com.mik0war.complimentsApp.data.CommonDataModel

class ComplimentRealmToCommonDataMapper : RealmToCommonDataMapper<ComplimentRealm> {
    override fun map(realmObject: ComplimentRealm): CommonDataModel =
        CommonDataModel(realmObject.id, realmObject.text, true)
}