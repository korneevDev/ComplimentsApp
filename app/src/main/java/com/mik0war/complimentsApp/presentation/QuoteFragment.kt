package com.mik0war.complimentsApp.presentation

import com.mik0war.complimentsApp.R

class QuoteFragment : BaseFragment<QuoteViewMode>() {
    override fun getViewModelClass() = QuoteViewMode::class.java
    override fun checkBoxText() = R.string.show_favorite_quotes
    override fun actionButtonText() = R.string.get_quote_button
}