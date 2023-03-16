package com.mik0war.complimentsApp.core.domain
interface CommonInteractor : SingleItemInteractor, ListItemInteractor, PersistentDataInteractor{
    fun getFavoriteItems(flag: Boolean)
}