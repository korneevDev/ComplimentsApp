package com.mik0war.complimentsApp.data

import io.realm.Realm
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

interface RealmProvider {
    fun provide() : Realm
}

class BaseRealmProvider() : RealmProvider {
    override fun provide(): Realm = Realm.getDefaultInstance()
}

open class ComplimentRealm : RealmObject() {
    @PrimaryKey
    var id = ""
    var text = ""
}

open class QuoteRealm : RealmObject() {
    @PrimaryKey
    var id = ""
    var text = ""
}