package com.mik0war.complimentsApp.core.data

import com.mik0war.complimentsApp.base.data.CommonDataModel

interface DataFetcher {
    suspend fun getData() : CommonDataModel
}

