package com.mik0war.complimentsApp.presentation.fragment

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
import com.mik0war.complimentsApp.core.presentation.FavoriteItemClickListener
import com.mik0war.complimentsApp.presentation.BaseViewModel
import com.mik0war.complimentsApp.presentation.recyclerView.CommonRecyclerViewAdapter
import com.mik0war.complimentsApp.presentation.FavoriteDataView

abstract class BaseFragment<T : BaseViewModel> : Fragment() {
    private lateinit var viewModel: BaseViewModel
    fun tag() : String = javaClass.simpleName
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.common_view, container, false)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = (requireActivity().application as ComplimentsApp)
            .get(getViewModelClass(), requireActivity())
    }

    protected abstract fun getViewModelClass(): Class<T>

    @StringRes
    protected abstract fun checkBoxText() : Int
    @StringRes
    protected abstract fun actionButtonText() : Int


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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
        }, viewModel)
        recyclerView.adapter = adapter

        viewModel.observeList(this) {
            adapter.update()
        }

        viewModel.getItemList()
    }

    override fun onPause() {
        viewModel.saveIsFavoritesState()
        super.onPause()
    }
}