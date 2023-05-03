package com.mik0war.complimentsApp.presentation.recyclerView

import androidx.recyclerview.widget.DiffUtil
import com.mik0war.complimentsApp.presentation.CommonUIModel

class CommonDiffUtilCallback(
    private val oldList: List<CommonUIModel>,
    private val newList: List<CommonUIModel>
) : DiffUtil.Callback() {
    override fun getOldListSize() = oldList.size

    override fun getNewListSize() = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) =
        oldList[oldItemPosition].same(newList[newItemPosition])

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) = false
}