package com.mik0war.complimentsApp.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mik0war.complimentsApp.R
import com.mik0war.complimentsApp.core.presentation.FavoriteItemClickListener
import com.mik0war.complimentsApp.core.presentation.ListGetter

class CommonRecyclerViewAdapter(
    private val listener: FavoriteItemClickListener,
    private val communication: ListGetter
    ) : RecyclerView.Adapter<CommonDataViewHolder>() {

    fun update(){
        val diffResult = communication.getDiffResult()
        diffResult.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommonDataViewHolder {
        val emptyList = viewType == 0
        val view = LayoutInflater.from(parent.context).inflate(
            if (emptyList)
                R.layout.empty_data_item
            else R.layout.common_data_item,
            parent, false)
        return if (!emptyList) CommonDataViewHolder.Base(listener, view) else CommonDataViewHolder.Empty(view)
    }

    override fun getItemCount(): Int = communication.getList().size

    override fun onBindViewHolder(holder: CommonDataViewHolder, position: Int) {
        holder.bind(communication.getList()[position])
    }

    override fun getItemViewType(position: Int): Int = when(communication.getList()[position]){
        is FailedCommonUIModel -> 0
        else -> 1
    }
}