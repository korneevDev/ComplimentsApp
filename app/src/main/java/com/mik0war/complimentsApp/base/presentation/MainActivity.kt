package com.mik0war.complimentsApp.base.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mik0war.complimentsApp.ComplimentsApp
import com.mik0war.complimentsApp.R

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel : BaseViewModel
    private lateinit var quoteViewModel: BaseViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = (application as ComplimentsApp).baseViewModel

        val favoriteDataView = findViewById<FavoriteDataView>(R.id.favorite_data_view)
        favoriteDataView.linkWith(viewModel)
        viewModel.observe(this) { state ->
            favoriteDataView.show(state)
        }

        quoteViewModel = (application as ComplimentsApp).quoteViewModel

        val quotesDataView = findViewById<FavoriteDataView>(R.id.favorite_quotes_view)
        quotesDataView.linkWith(quoteViewModel)
        quoteViewModel.observe(this) { state ->
            quotesDataView.show(state)
        }
    }
}