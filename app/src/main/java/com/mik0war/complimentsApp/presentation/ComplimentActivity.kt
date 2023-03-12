package com.mik0war.complimentsApp.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.mik0war.complimentsApp.ComplimentsApp
import com.mik0war.complimentsApp.R

class ComplimentActivity : AppCompatActivity() {
    private lateinit var viewModel : BaseViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.compliment_layout)

        viewModel = (application as ComplimentsApp).baseViewModel

        val favoriteDataView = findViewById<FavoriteDataView>(R.id.favorite_data_view)
        favoriteDataView.linkWith(viewModel)
        viewModel.observe(this) { state ->
            favoriteDataView.show(state)
        }

        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
        val adapter = CommonRecyclerViewAdapter()
        recyclerView.adapter = adapter

        viewModel.observeList(this) {
            adapter.show(it)
        }

        viewModel.getItemList()
    }
}