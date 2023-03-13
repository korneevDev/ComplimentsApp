package com.mik0war.complimentsApp.presentation

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mik0war.complimentsApp.R
import com.mik0war.complimentsApp.core.presentation.CommonCommunication
import com.mik0war.complimentsApp.core.presentation.FavoriteItemClickListener

class CommonRecyclerViewAdapter(
    private val listener: FavoriteItemClickListener,
    private val communication: CommonCommunication
    ) : RecyclerView.Adapter<CommonDataViewHolder>() {

    @SuppressLint("NotifyDataSetChanged")
    fun update(){
        notifyDataSetChanged()
    }

    fun update(pair: Pair<Boolean, Int>){
        if(pair.first)
            notifyItemInserted(pair.second)
        else
            notifyItemRemoved(pair.second)
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