package com.mik0war.complimentsApp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    private lateinit var viewmodel : ViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewmodel = (application as ComplimentsApp).viewModel

        val processBar = findViewById<ProgressBar>(R.id.progress_bar)
        processBar.visibility = View.INVISIBLE

        val textView = findViewById<TextView>(R.id.compliment_view)
        val getButton = findViewById<Button>(R.id.get_button)

        getButton.setOnClickListener {
            getButton.isEnabled = false
            getButton.visibility = View.INVISIBLE
            textView.visibility = View.INVISIBLE
            processBar.visibility = View.VISIBLE
            viewmodel.getJoke()
        }

        viewmodel.init(object : TextProvider{
            override fun provideText(text: String) = runOnUiThread{
                processBar.visibility = View.INVISIBLE
                getButton.visibility = View.VISIBLE
                textView.text = text
                textView.visibility = View.VISIBLE
                getButton.isEnabled = true
            }
        })
    }

    override fun onDestroy() {
        viewmodel.clear()
        super.onDestroy()
    }
}