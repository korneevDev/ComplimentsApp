package com.mik0war.complimentsApp.core.data.cache

import com.mik0war.complimentsApp.base.data.CommonDataModel
import io.realm.RealmObject

interface RealmToCommonDataMapper<T : RealmObject> {
    fun map(realmObject : T) : CommonDataModel
}

