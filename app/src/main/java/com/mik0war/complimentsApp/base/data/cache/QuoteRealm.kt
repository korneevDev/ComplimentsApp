package com.mik0war.complimentsApp.base.data.cache

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class QuoteRealm : RealmObject() {
    @PrimaryKey
    var id = ""
    var text = ""
}