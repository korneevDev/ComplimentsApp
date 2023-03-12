package com.mik0war.complimentsApp.base.presentation

import androidx.annotation.DrawableRes
import com.mik0war.complimentsApp.core.presentation.EnableButton
import com.mik0war.complimentsApp.core.presentation.ShowImage
import com.mik0war.complimentsApp.core.presentation.ShowProgress
import com.mik0war.complimentsApp.core.presentation.ShowText

sealed class State{
    protected abstract val type: Int
    companion object{
        const val INITIAL = 0
        const val PROGRESS = 1
        const val FAILED = 2
    }

    fun isType(type : Int) = this.type == type

    object Progress : State() {
        override val type = PROGRESS

        override fun show(
            progress: ShowProgress,
            button: EnableButton,
        ) {
            progress.show(true)
            button.enable(false)
        }
    }

    abstract class Info(private val text: String, @DrawableRes private val id: Int) : State(){
        override fun show(
            progress: ShowProgress,
            button: EnableButton,
        ) {
            progress.show(false)
            button.enable(true)
        }

        override fun show(
            textView: ShowText,
            imageButton: ShowImage
        ) {
            textView.show(text)
            imageButton.show(id)
        }
    }

    open class Initial(text: String, id: Int) : Info(text, id){
        override val type = INITIAL
    }
    class Failed(text: String, id: Int) : Initial(text, id){
        override val type = FAILED
    }

    fun show(
        progress: ShowProgress,
        button: EnableButton,
        textView: ShowText,
        imageButton: ShowImage
    ){
        show(progress, button)
        show(textView, imageButton)
    }

    protected open fun show(
        progress: ShowProgress,
        button: EnableButton
    ) {}

    protected open fun show(
        textView: ShowText,
        imageButton: ShowImage
    ) {}
}