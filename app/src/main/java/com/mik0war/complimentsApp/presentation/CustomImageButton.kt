package com.mik0war.complimentsApp.presentation

import android.content.Context
import android.util.AttributeSet

class CustomImageButton : androidx.appcompat.widget.AppCompatImageButton, ShowImage {
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    override fun show(arg: Int) {
        setImageResource(arg)
    }

}
