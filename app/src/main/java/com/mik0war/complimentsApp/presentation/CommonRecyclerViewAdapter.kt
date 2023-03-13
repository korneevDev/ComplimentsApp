package com.mik0war.complimentsApp.presentation

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mik0war.complimentsApp.R

class CommonRecyclerViewAdapter : RecyclerView.Adapter<CommonRecyclerViewAdapter.CommonDataViewHolder>() {
    private val list = ArrayList<CommonUIModel>()

    @SuppressLint("NotifyDataSetChanged")
    fun show(data : List<CommonUIModel>){
        list.clear()
        list.addAll(data)
        notifyDataSetChanged()
    }

    open inner class CommonDataViewHolder(view : View) : RecyclerView.ViewHolder(view){
        private val textView = itemView.findViewById<CustomTextView>(R.id.commonDataTextView)

        fun bind(item : CommonUIModel) = item.map(textView)
    }

    inner class EmptyDataViewHolder(view: View) : CommonDataViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommonDataViewHolder {
        val emptyList = viewType == 0
        val view = LayoutInflater.from(parent.context).inflate(
            if (emptyList)
                R.layout.empty_data_item
            else R.layout.common_data_item,

            parent, false)
        return if (!emptyList) CommonDataViewHolder(view) else EmptyDataViewHolder(view)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: CommonDataViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemViewType(position: Int): Int = when(list[position]){
        is FailedCommonUIModel -> 0
        else -> 1
    }
}