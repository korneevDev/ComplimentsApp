package com.mik0war.complimentsApp

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class ComplimentRealm() : RealmObject() {
    @PrimaryKey
    var id = ""
    var text = ""
}