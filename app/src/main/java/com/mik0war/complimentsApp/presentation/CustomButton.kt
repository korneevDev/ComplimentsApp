package com.mik0war.complimentsApp.presentation

import android.content.Context
import android.util.AttributeSet

class CustomButton : androidx.appcompat.widget.AppCompatButton, EnableButton {
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    override fun enable(isEnable: Boolean) {
        isEnabled = isEnable
    }


}