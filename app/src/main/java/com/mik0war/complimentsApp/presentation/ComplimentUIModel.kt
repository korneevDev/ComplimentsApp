package com.mik0war.complimentsApp.presentation

import androidx.annotation.DrawableRes
import com.mik0war.complimentsApp.Communication
import com.mik0war.complimentsApp.R

class BaseComplimentUIModel(text: String) : ComplimentUIModel(text) {
    override fun getIconResId(): Int = R.drawable.favorite_empty
}

class FavoriteComplimentUIModel(text: String) : ComplimentUIModel(text) {
    override fun getIconResId(): Int = R.drawable.favorite_fill
}

class FailedComplimentUIModel(text: String) : ComplimentUIModel(text) {
    override fun getIconResId(): Int = 0
    override fun getData(communication: Communication) = communication.showState(
        State.Failed(text(), getIconResId())
    )
}

abstract class ComplimentUIModel (
    private val text : String
){
    protected fun text() = text
    @DrawableRes
    protected abstract fun getIconResId() : Int
    open fun getData(communication: Communication) {
        communication.showState(
            State.Initial(text(), getIconResId())
        )
    }
}