package com.mik0war.complimentsApp.presentation

import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class PagerAdapter(activity: FragmentActivity) : FragmentStateAdapter(activity) {
    override fun getItemCount() = 2

    override fun createFragment(position: Int) =
        if(position == 0)
            ComplimentFragment()
        else
            QuoteFragment()
}