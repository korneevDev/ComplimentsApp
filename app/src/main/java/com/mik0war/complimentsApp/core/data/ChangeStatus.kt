package com.mik0war.complimentsApp.core.data

import com.mik0war.complimentsApp.data.CommonDataModel

interface ChangeStatus {
    suspend fun addOrRemove(id : String, model: CommonDataModel) : CommonDataModel
}