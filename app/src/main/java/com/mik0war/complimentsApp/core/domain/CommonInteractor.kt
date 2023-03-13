package com.mik0war.complimentsApp.core.domain

import com.mik0war.complimentsApp.domain.CommonItem

interface CommonInteractor {
    suspend fun getItem() : CommonItem
    suspend fun getItemList() : List<CommonItem>
    suspend fun changeFavorites() : CommonItem
    fun getFavoriteItems(flag: Boolean)

    suspend fun removeItem(id: String)
}