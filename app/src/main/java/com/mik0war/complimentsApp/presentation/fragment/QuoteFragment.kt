package com.mik0war.complimentsApp.presentation.fragment

import com.mik0war.complimentsApp.R
import com.mik0war.complimentsApp.presentation.QuoteViewMode

class QuoteFragment : BaseFragment<QuoteViewMode>() {
    override fun getViewModelClass() = QuoteViewMode::class.java
    override fun checkBoxText() = R.string.show_favorite_quotes
    override fun actionButtonText() = R.string.get_quote_button
}