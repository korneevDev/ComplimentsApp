package com.mik0war.complimentsApp.base.data

import com.mik0war.complimentsApp.core.data.CommonDataModelMapper
import com.mik0war.complimentsApp.base.domain.CommonItem

class CommonSuccessMapper : CommonDataModelMapper<CommonItem.Success> {
    override fun map(id: String, text: String, isCached: Boolean) =
        CommonItem.Success(text, isCached)
}

