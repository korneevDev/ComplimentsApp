package com.mik0war.complimentsApp.presentation

import android.content.Context
import com.mik0war.complimentsApp.core.ResourceManager

class BaseResourceManager(private val context : Context) : ResourceManager {

    override fun getString(resId: Int): String = context.getString(resId)
}