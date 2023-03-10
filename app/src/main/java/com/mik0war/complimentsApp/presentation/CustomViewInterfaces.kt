package com.mik0war.complimentsApp.presentation

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.ProgressBar

interface Show<T>{
    fun show(arg : T)
}
interface ShowText : Show<String>
class CustomTextView : androidx.appcompat.widget.AppCompatTextView, ShowText {
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    override fun show(arg: String) {
        this.text = arg
    }
}

interface ShowProgress : Show<Boolean>
class CustomProgressBar : ProgressBar, ShowProgress {
    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    override fun show(arg: Boolean) {
        visibility = if(arg) View.VISIBLE else View.INVISIBLE
    }
}

interface ShowImage : Show<Int>
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

interface EnableButton{
    fun enable(isEnable: Boolean)
}

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