package com.mik0war.complimentsApp.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mik0war.complimentsApp.R

class MainActivity : AppCompatActivity() {
    private lateinit var viewmodel : BaseViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewmodel = (application as ComplimentsApp).baseViewModel

        val favoriteDataView = findViewById<FavoriteDataView>(R.id.favorite_data_view)

        favoriteDataView.listenChanges {isChecked ->
            viewmodel.changeDataSource(isChecked)
        }

        favoriteDataView.handleChangeButton {
            viewmodel.changeComplimentStatus()
        }

        favoriteDataView.handleActionButton {
            viewmodel.getCompliment()
        }

        viewmodel.observe(this) { state ->
                favoriteDataView.show(state)
        }
    }


}