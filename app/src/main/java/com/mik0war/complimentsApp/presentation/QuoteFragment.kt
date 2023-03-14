package com.mik0war.complimentsApp.presentation

import com.mik0war.complimentsApp.ComplimentsApp
import com.mik0war.complimentsApp.R

class QuoteFragment : BaseFragment() {
    override fun getViewModel(app: ComplimentsApp) = app.quoteViewModel

    override fun getCommunication(app: ComplimentsApp) = app.quoteCommunication

    override fun checkBoxText() = R.string.show_favorite_quotes

    override fun actionButtonText() = R.string.get_quote_button
}