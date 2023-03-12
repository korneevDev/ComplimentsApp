package com.mik0war.complimentsApp.domain

import com.mik0war.complimentsApp.presentation.BaseCommonUIModel
import com.mik0war.complimentsApp.presentation.CommonUIModel
import com.mik0war.complimentsApp.presentation.FailedCommonUIModel
import com.mik0war.complimentsApp.presentation.FavoriteCommonUIModel
import com.mik0war.complimentsApp.core.Mapper

sealed class CommonItem  : Mapper<CommonUIModel>{
    class Success(
        private val text : String,
        private val isFavorite : Boolean
    ) : CommonItem() {
        override fun to(): CommonUIModel {
            return if (isFavorite)
                FavoriteCommonUIModel(text)
            else
                BaseCommonUIModel(text)
        }
    }

    class Failed(
        private val failure : Failure
    ) : CommonItem() {
        override fun to(): CommonUIModel {
            return FailedCommonUIModel(failure.getErrorMessage())
        }
    }
}