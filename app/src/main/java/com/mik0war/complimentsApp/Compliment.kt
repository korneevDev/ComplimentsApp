package com.mik0war.complimentsApp

import androidx.annotation.DrawableRes

class BaseCompliment(text: String) : Compliment(text) {
    override fun getIconResId(): Int = R.drawable.favorite_empty
}

class FavoriteCompliment(text: String) : Compliment(text) {
    override fun getIconResId(): Int = R.drawable.favorite_fill
}

class FailedCompliment(text: String) : Compliment(text) {
    override fun getIconResId(): Int = 0
}

abstract class Compliment (
    private val text : String
){
    protected fun getComplimentUI() = text

    @DrawableRes
    protected abstract fun getIconResId() : Int

    fun map(callBack: DataProvider) = callBack.run {
        provideText(getComplimentUI())
        provideIconId(getIconResId())
    }
}