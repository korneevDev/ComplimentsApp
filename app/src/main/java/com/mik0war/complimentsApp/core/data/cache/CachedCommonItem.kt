package com.mik0war.complimentsApp.core.data.cache

import com.mik0war.complimentsApp.core.data.ChangeCommonItem
import com.mik0war.complimentsApp.data.CommonDataModel

interface CachedCommonItem : ChangeCommonItem {
    fun save(commonDataModel: CommonDataModel)
    fun clear()
}