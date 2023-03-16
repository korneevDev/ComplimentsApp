package com.mik0war.complimentsApp.core.domain

import com.mik0war.complimentsApp.domain.CommonItem

interface ListItemInteractor {
    suspend fun getItemList() : List<CommonItem>
    suspend fun removeItem(id: String)
    suspend fun checkId(id: String) : Boolean
}