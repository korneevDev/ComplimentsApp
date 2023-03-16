package com.mik0war.complimentsApp.core.domain

import com.mik0war.complimentsApp.domain.CommonItem

interface SingleItemInteractor {
    suspend fun getItem() : CommonItem
    suspend fun changeFavorites() : CommonItem
}