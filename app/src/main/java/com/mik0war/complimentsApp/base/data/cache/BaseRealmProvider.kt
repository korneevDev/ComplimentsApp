package com.mik0war.complimentsApp.base.data.cache

import com.mik0war.complimentsApp.core.data.cache.RealmProvider
import io.realm.Realm

class BaseRealmProvider() : RealmProvider {
    override fun provide(): Realm = Realm.getDefaultInstance()
}