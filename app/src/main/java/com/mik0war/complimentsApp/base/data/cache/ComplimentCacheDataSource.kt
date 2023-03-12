package com.mik0war.complimentsApp.base.data.cache

import com.mik0war.complimentsApp.core.data.cache.RealmProvider
import com.mik0war.complimentsApp.base.data.mapper.ComplimentRealmMapper

class ComplimentCacheDataSource(realmProvider: RealmProvider,
                                mapper: ComplimentRealmMapper,
                                commonDataMapper: ComplimentRealmToCommonDataMapper
) :
    BaseCacheDataSource<ComplimentRealm>(realmProvider, mapper, commonDataMapper) {
    override val dbClass = ComplimentRealm::class.java
}