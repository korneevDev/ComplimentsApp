package com.mik0war.complimentsApp.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.tabs.TabLayout
import com.mik0war.complimentsApp.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val tabLayout = findViewById<TabLayout>(R.id.tab_layout)
        val tabChosen: (Boolean) -> Unit = { contentChosen ->
            if (contentChosen)
                show(ComplimentFragment())
            else
                show(QuoteFragment())
        }
        tabLayout.addOnTabSelectedListener(TabLayoutListener(tabChosen))
        show(ComplimentFragment())
    }

    private fun show(fragment: BaseFragment<*>){
        if(supportFragmentManager.fragments.isEmpty() ||
                supportFragmentManager.fragments.last().tag != fragment.tag()) {
            supportFragmentManager.beginTransaction().replace(R.id.container, fragment, fragment.tag()).commit()
        }
    }

    private inner class TabLayoutListener(private val tabChosen : (Boolean) -> Unit)
        : TabLayout.OnTabSelectedListener {
        override fun onTabSelected(tab: TabLayout.Tab?) = tabChosen.invoke(tab?.position == 0)
        override fun onTabUnselected(tab: TabLayout.Tab?) = Unit
        override fun onTabReselected(tab: TabLayout.Tab?) = Unit
    }
}