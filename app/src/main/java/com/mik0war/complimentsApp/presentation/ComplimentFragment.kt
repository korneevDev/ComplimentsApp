package com.mik0war.complimentsApp.presentation

import com.mik0war.complimentsApp.ComplimentsApp
import com.mik0war.complimentsApp.R
import com.mik0war.complimentsApp.core.presentation.CommonCommunication

class ComplimentFragment : BaseFragment() {
    override fun getViewModel(app: ComplimentsApp): BaseViewModel =
        app.complimentViewModel

    override fun getCommunication(app: ComplimentsApp): CommonCommunication =
        app.complimentCommunication

    override fun checkBoxText(): Int = R.string.show_favorite_compliment

    override fun actionButtonText(): Int = R.string.get_button
}