package com.mik0war.complimentsApp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import com.mik0war.complimentsApp.presentation.ComplimentViewMode
import com.mik0war.complimentsApp.presentation.QuoteViewMode

class ViewModelsFactory(
    private val complimentModule: ComplimentModule,
    private val quoteModule: QuoteModule
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
        val viewModule = when{
            modelClass.isAssignableFrom(ComplimentViewMode::class.java) -> complimentModule
            modelClass.isAssignableFrom(QuoteViewMode::class.java) -> quoteModule
            else -> throw IllegalStateException("unknown type of viewModel")
        }
        return viewModule.getViewModel() as T
    }
}