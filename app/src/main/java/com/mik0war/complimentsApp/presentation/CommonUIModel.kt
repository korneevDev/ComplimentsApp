package com.mik0war.complimentsApp.presentation

import androidx.annotation.DrawableRes
import com.mik0war.complimentsApp.R
import com.mik0war.complimentsApp.core.presentation.Communication
import com.mik0war.complimentsApp.core.presentation.FavoriteItemClickListener
import com.mik0war.complimentsApp.core.presentation.ShowText

class BaseCommonUIModel(text: String) : CommonUIModel(text) {
    override fun getIconResId(): Int = R.drawable.favorite_empty
}

class FavoriteCommonUIModel(private val id: String, text: String) : CommonUIModel(text) {
    override fun getIconResId(): Int = R.drawable.favorite_fill
    override fun change(listener: FavoriteItemClickListener) {
        listener.change(id)
    }

    override fun matches(id: String): Boolean = this.id == id
    override fun same(commonUIModel: CommonUIModel): Boolean {
        return commonUIModel is FailedCommonUIModel && commonUIModel.matches(this.id)
    }
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

    open fun change(listener: FavoriteItemClickListener) = Unit
    open fun matches(id: String) : Boolean = false
    open fun same(commonUIModel: CommonUIModel) = false
}