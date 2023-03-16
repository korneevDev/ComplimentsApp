package com.mik0war.complimentsApp.presentation

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.CheckBox
import android.widget.LinearLayout
import androidx.annotation.StringRes
import com.mik0war.complimentsApp.R
import com.mik0war.complimentsApp.core.presentation.CommonViewModel

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
        textView = findViewById(R.id.data_view)
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

    fun linkWith(commonViewModel: CommonViewModel){
        checkBox.setOnCheckedChangeListener{_, isChecked ->
            commonViewModel.changeDataSource(isChecked)
        }
        checkBox.isChecked = commonViewModel.loadIsFavoritesState()

        favoriteIcon.setOnClickListener{
            commonViewModel.changeItemStatus()
        }

        button.setOnClickListener{
            commonViewModel.getItem()
        }
    }

    fun checkBoxText(@StringRes resId: Int) = checkBox.setText(resId)
    fun actionButtonText(@StringRes resId: Int) = button.setText(resId)

    fun show(state : State) = state.show(progressBar, button, textView, favoriteIcon)
}