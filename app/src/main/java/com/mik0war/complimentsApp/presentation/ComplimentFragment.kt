package com.mik0war.complimentsApp.presentation

import com.mik0war.complimentsApp.R

class ComplimentFragment : BaseFragment<ComplimentViewMode>() {
    override fun getViewModelClass(): Class<ComplimentViewMode> =
        ComplimentViewMode::class.java

    override fun checkBoxText(): Int = R.string.show_favorite_compliment

    override fun actionButtonText(): Int = R.string.get_button
}