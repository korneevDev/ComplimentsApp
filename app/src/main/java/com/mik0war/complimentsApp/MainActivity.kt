package com.mik0war.complimentsApp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.CheckBox

class MainActivity : AppCompatActivity() {
    private lateinit var viewmodel : BaseViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewmodel = (application as ComplimentsApp).baseViewModel

        val checkBox = findViewById<CheckBox>(R.id.checkbox)
        checkBox.setOnCheckedChangeListener{_, isChecked ->
            viewmodel.changeDataSource(isChecked)
        }

        val processBar = findViewById<CustomProgressBar>(R.id.progress_bar)
        processBar.visibility = View.INVISIBLE

        val textView = findViewById<CustomTextView>(R.id.compliment_view)
        val getButton = findViewById<CustomButton>(R.id.get_button)
        val favoriteIcon = findViewById<CustomImageButton>(R.id.favorite_icon)

        favoriteIcon.setOnClickListener{
            viewmodel.changeComplimentStatus()
        }

        getButton.setOnClickListener {
            viewmodel.getCompliment()
        }

        viewmodel.observe(this) { state ->
                state.show(processBar, getButton, textView, favoriteIcon)
        }
    }
}