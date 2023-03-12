package com.mik0war.complimentsApp.data

import io.realm.RealmObject

interface RealmToCommonDataMapper<T : RealmObject> {
    fun map(realmObject : T) : CommonDataModel
}

class ComplimentRealmToCommonDataMapper : RealmToCommonDataMapper<ComplimentRealm> {
    override fun map(realmObject: ComplimentRealm): CommonDataModel =
        CommonDataModel(realmObject.id, realmObject.text, true)
}

class QuoteRealmToCommonDataMapper : RealmToCommonDataMapper<QuoteRealm> {
    override fun map(realmObject: QuoteRealm): CommonDataModel =
        CommonDataModel(realmObject.id, realmObject.text, true)
}