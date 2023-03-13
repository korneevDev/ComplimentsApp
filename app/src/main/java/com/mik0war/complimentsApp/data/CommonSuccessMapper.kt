package com.mik0war.complimentsApp.data

import com.mik0war.complimentsApp.core.data.CommonDataModelMapper
import com.mik0war.complimentsApp.domain.CommonItem

class CommonSuccessMapper : CommonDataModelMapper<CommonItem.Success> {
    override fun map(id: String, text: String, isCached: Boolean) =
        CommonItem.Success(id, text, isCached)
}

