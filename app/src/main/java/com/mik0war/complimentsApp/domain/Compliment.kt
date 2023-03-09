package com.mik0war.complimentsApp.domain

import com.mik0war.complimentsApp.ComplimentError
import com.mik0war.complimentsApp.presentation.BaseComplimentUIModel
import com.mik0war.complimentsApp.presentation.ComplimentUIModel
import com.mik0war.complimentsApp.presentation.FailedComplimentUIModel
import com.mik0war.complimentsApp.presentation.FavoriteComplimentUIModel
import com.mik0war.complimentsApp.core.Mapper

sealed class Compliment  : Mapper<ComplimentUIModel>{
    class Success(
        private val text : String,
        private val isFavorite : Boolean
    ) : Compliment() {
        override fun to(): ComplimentUIModel {
            return if (isFavorite)
                FavoriteComplimentUIModel(text)
            else
                BaseComplimentUIModel(text)
        }
    }

    class Failed(
        private val failure : ComplimentError
    ) : Compliment() {
        override fun to(): ComplimentUIModel {
            return FailedComplimentUIModel(failure.getErrorMessage())
        }
    }
}