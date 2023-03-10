package com.mik0war.complimentsApp.presentation

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.CheckBox
import android.widget.LinearLayout
import com.mik0war.complimentsApp.R

class FavoriteDataView : LinearLayout {
    private val checkBox : CheckBox
    private val textView : CustomTextView
    private val button : CustomButton
    private val progressBar : CustomProgressBar
    private val favoriteIcon : CustomImageButton

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    init {
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