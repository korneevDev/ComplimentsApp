package com.mik0war.complimentsApp.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.mik0war.complimentsApp.ComplimentsApp
import com.mik0war.complimentsApp.R
import com.mik0war.complimentsApp.core.presentation.CommonCommunication
import com.mik0war.complimentsApp.core.presentation.FavoriteItemClickListener

abstract class BaseFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.common_view, container, false)

    protected abstract fun getViewModel(app: ComplimentsApp): BaseViewModel
    protected abstract fun getCommunication(app: ComplimentsApp): CommonCommunication

    @StringRes
    protected abstract fun checkBoxText() : Int
    @StringRes
    protected abstract fun actionButtonText() : Int


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val application = requireActivity().application as ComplimentsApp
        val viewModel = getViewModel(application)
        val communication = getCommunication(application)

        val favoriteDataView = view.findViewById<FavoriteDataView>(R.id.favorite_data_view)
        favoriteDataView.linkWith(viewModel)
        viewModel.observe(this) { state ->
            favoriteDataView.show(state)
        }

        favoriteDataView.checkBoxText(checkBoxText())
        favoriteDataView.actionButtonText(actionButtonText())

        val recyclerView = view.findViewById<RecyclerView>(R.id.recycler_view)

        val adapter = CommonRecyclerViewAdapter(object : FavoriteItemClickListener {
            override fun change(id: String) {
                Snackbar.make(
                    favoriteDataView,
                    R.string.remove_from_favorites_question,
                    Snackbar.LENGTH_SHORT
                ).setAction(R.string.remove_from_favorites_answer) {
                    viewModel.changeItemStatus(id)
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