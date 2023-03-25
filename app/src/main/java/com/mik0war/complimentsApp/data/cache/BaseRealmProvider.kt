package com.mik0war.complimentsApp.data.cache

import android.content.Context
import com.mik0war.complimentsApp.core.data.cache.RealmProvider
import io.realm.Realm
import io.realm.RealmConfiguration

class BaseRealmProvider(
    context: Context,
    useMocks: Boolean
) : RealmProvider {
    init {
        Realm.init(context)
        val fileName = if(useMocks) BASE_NAME else MOCK_NAME
        val config = RealmConfiguration.Builder().name(fileName).build()

        Realm.setDefaultConfiguration(config)
    }
    override fun provide(): Realm = Realm.getDefaultInstance()

    companion object{
        const val BASE_NAME = "complimentsRealm"
        const val MOCK_NAME = "complimentsRealmMocks"
    }
}