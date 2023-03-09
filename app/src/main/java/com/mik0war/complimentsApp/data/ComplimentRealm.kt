package com.mik0war.complimentsApp.data

import com.mik0war.complimentsApp.core.Mapper
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class ComplimentRealm : RealmObject(), Mapper<ComplimentDataModel> {
    @PrimaryKey
    var id = ""
    var text = ""

    override fun to() = ComplimentDataModel(text, true)
}