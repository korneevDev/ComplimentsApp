package com.mik0war.complimentsApp.core.data.cache

import io.realm.Realm

interface RealmProvider {
    fun provide() : Realm
}