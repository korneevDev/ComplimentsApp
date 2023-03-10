package com.mik0war.complimentsApp.presentation

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.CheckBox
import android.widget.LinearLayout
import com.mik0war.complimentsApp.R

class FavoriteDataView : LinearLayout {
    private lateinit var checkBox : CheckBox
    private lateinit var textView : CustomTextView
    private lateinit var button : CustomButton
    private lateinit var progressBar : CustomProgressBar
    private lateinit var favoriteIcon : CustomImageButton

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet) : super(context, attrs){
        init(attrs)
    }
    constructor(context: Context?, attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ){
        init(attrs)
    }

    private fun init(attrs: AttributeSet) {
        orientation = VERTICAL
        (context
            .getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater)
            .inflate(R.layout.favorite_data_view, this, true)

        checkBox = getChildAt(0) as CheckBox
        textView = findViewById(R.id.compliment_view)
        button = findViewById(R.id.get_button)
        progressBar = findViewById(R.id.progress_bar)
        progressBar.visibility = View.INVISIBLE
        favoriteIcon = findViewById(R.id.favorite_icon)

        context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.FavoriteDataView,
            0, 0).apply {
            try{
                val buttonText = getString(R.styleable.FavoriteDataView_buttonText)
                button.text = buttonText

                val checkBoxText = getString(R.styleable.FavoriteDataView_checkBoxText)
                checkBox.text = checkBoxText
            } finally {
                recycle()
            }
        }
    }

    fun listenChanges(block: (checked: Boolean) -> Unit) =
        checkBox.setOnCheckedChangeListener{_, isChecked ->
            block.invoke(isChecked)
        }

    fun handleChangeButton(block : () -> Unit) =
        favoriteIcon.setOnClickListener{
            block.invoke()
        }

    fun handleActionButton(block: () -> Unit) =
        button.setOnClickListener{
            block.invoke()
        }

    fun show(state : State) = state.show(progressBar, button, textView, favoriteIcon)
}