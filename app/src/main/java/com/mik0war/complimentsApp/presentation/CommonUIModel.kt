package com.mik0war.complimentsApp.presentation

import androidx.annotation.DrawableRes
import com.mik0war.complimentsApp.R
import com.mik0war.complimentsApp.core.presentation.Communication
import com.mik0war.complimentsApp.core.presentation.ShowText

class BaseCommonUIModel(text: String) : CommonUIModel(text) {
    override fun getIconResId(): Int = R.drawable.favorite_empty
}

class FavoriteCommonUIModel(text: String) : CommonUIModel(text) {
    override fun getIconResId(): Int = R.drawable.favorite_fill
}

class FailedCommonUIModel(text: String) : CommonUIModel(text) {
    override fun getIconResId(): Int = 0
    override fun getData(communication: Communication) = communication.showState(
        State.Failed(text(), getIconResId())
    )
}

abstract class CommonUIModel (
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

    fun map(showText: ShowText) = showText.show(text)
}