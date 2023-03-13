package com.mik0war.complimentsApp.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.mik0war.complimentsApp.ComplimentsApp
import com.mik0war.complimentsApp.R
import com.mik0war.complimentsApp.core.presentation.FavoriteItemClickListener

class ComplimentActivity : AppCompatActivity() {
    private lateinit var viewModel : BaseViewModel
    private lateinit var adapter: CommonRecyclerViewAdapter

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

        val communication = (application as ComplimentsApp).complimentCommunication

        adapter = CommonRecyclerViewAdapter(object : FavoriteItemClickListener {
            override fun change(id: String) {
                    Snackbar.make(
                        favoriteDataView,
                        R.string.remove_from_favorites_question,
                        Snackbar.LENGTH_SHORT
                    ).setAction(R.string.remove_from_favorites_answer) {
                        val position = viewModel.changeItemStatus(id)
                        adapter.update(Pair(false, position))
                    }.show()
            }
        }, communication)
        recyclerView.adapter = adapter

        viewModel.observeList(this) {
            adapter.update()
        }

        viewModel.getItemList()
    }
}