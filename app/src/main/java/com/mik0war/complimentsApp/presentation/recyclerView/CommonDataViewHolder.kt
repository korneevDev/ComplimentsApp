package com.mik0war.complimentsApp.presentation.recyclerView

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.mik0war.complimentsApp.R
import com.mik0war.complimentsApp.core.presentation.FavoriteItemClickListener
import com.mik0war.complimentsApp.presentation.CommonUIModel
import com.mik0war.complimentsApp.presentation.CustomImageButton
import com.mik0war.complimentsApp.presentation.CustomTextView

abstract class CommonDataViewHolder(view : View) : RecyclerView.ViewHolder(view){
    private val textView = itemView.findViewById<CustomTextView>(R.id.commonDataTextView)

    open fun bind(item : CommonUIModel) = item.map(textView)

    class Base(private val listener: FavoriteItemClickListener, view: View) : CommonDataViewHolder(view){
        private val imageButton = itemView.findViewById<CustomImageButton>(R.id.change_button)

        override fun bind(item: CommonUIModel){
            super.bind(item)
            imageButton.setOnClickListener{
                item.change(listener)
            }
        }
    }

    class Empty(view: View) : CommonDataViewHolder(view)
}