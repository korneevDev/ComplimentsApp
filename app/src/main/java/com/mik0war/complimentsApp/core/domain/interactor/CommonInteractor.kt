package com.mik0war.complimentsApp.core.domain.interactor
interface CommonInteractor : SingleItemInteractor, ListItemInteractor, PersistentDataInteractor {
    fun getFavoriteItems(flag: Boolean)
}