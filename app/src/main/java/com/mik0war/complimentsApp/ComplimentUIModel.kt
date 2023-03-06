package com.mik0war.complimentsApp

import androidx.annotation.DrawableRes

class BaseComplimentUIModel(text: String) : ComplimentUIModel(text) {
    override fun getIconResId(): Int = R.drawable.favorite_empty
}

class FavoriteComplimentUIModel(text: String) : ComplimentUIModel(text) {
    override fun getIconResId(): Int = R.drawable.favorite_fill
}

class FailedComplimentUIModel(text: String) : ComplimentUIModel(text) {
    override fun getIconResId(): Int = 0
}

abstract class ComplimentUIModel (
    private val text : String
){
    protected fun getComplimentUI() = text
    @DrawableRes
    protected abstract fun getIconResId() : Int
    fun getData(communication: Communication) = communication.showData(Pair(getComplimentUI(), getIconResId()))
}