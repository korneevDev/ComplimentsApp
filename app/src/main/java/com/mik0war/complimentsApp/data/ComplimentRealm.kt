package com.mik0war.complimentsApp.data

import com.mik0war.complimentsApp.core.Mapper
import io.realm.Realm
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

interface RealmProvider {
    fun provide() : Realm
}

class BaseRealmProvider() : RealmProvider {
    override fun provide(): Realm = Realm.getDefaultInstance()
}

open class ComplimentRealm : RealmObject(), Mapper<ComplimentDataModel> {
    @PrimaryKey
    var id = ""
    var text = ""

    override fun to() = ComplimentDataModel(text, true)
}