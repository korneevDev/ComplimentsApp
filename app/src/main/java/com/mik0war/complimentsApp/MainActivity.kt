package com.mik0war.complimentsApp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.ImageButton
import android.widget.ProgressBar
import android.widget.TextView

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

        val processBar = findViewById<ProgressBar>(R.id.progress_bar)
        processBar.visibility = View.INVISIBLE

        val line = findViewById<View>(R.id.line)
        val textView = findViewById<TextView>(R.id.compliment_view)
        val getButton = findViewById<Button>(R.id.get_button)
        val favoriteIcon = findViewById<ImageButton>(R.id.favorite_icon)
        favoriteIcon.setOnClickListener{
            viewmodel.changeComplimentStatus()
        }


        getButton.setOnClickListener {
            getButton.isEnabled = false
            getButton.visibility = View.INVISIBLE
            textView.visibility = View.INVISIBLE
            checkBox.visibility = View.INVISIBLE
            line.visibility = View.INVISIBLE
            favoriteIcon.visibility = View.INVISIBLE

            processBar.visibility = View.VISIBLE
            viewmodel.getCompliment()
        }

        viewmodel.observe(this) { (text, iconResId) ->
            processBar.visibility = View.INVISIBLE
            textView.text = text
            getButton.visibility = View.VISIBLE
            textView.visibility = View.VISIBLE
            checkBox.visibility = View.VISIBLE
            line.visibility = View.VISIBLE
            favoriteIcon.visibility = View.VISIBLE
            getButton.isEnabled = true
            favoriteIcon.setImageResource(iconResId)
        }
    }
}