package com.mik0war.complimentsApp.base.data.cache

import com.mik0war.complimentsApp.core.data.cache.RealmProvider
import com.mik0war.complimentsApp.base.data.mapper.QuoteRealmMapper

class QuoteCacheDataSource(realmProvider: RealmProvider,
                           mapper: QuoteRealmMapper,
                           commonMapper: QuoteRealmToCommonDataMapper
) :
    BaseCacheDataSource<QuoteRealm>(realmProvider, mapper, commonMapper) {
    override val dbClass = QuoteRealm::class.java
}