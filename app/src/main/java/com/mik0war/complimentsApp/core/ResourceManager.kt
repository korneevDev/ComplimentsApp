package com.mik0war.complimentsApp.core

import androidx.annotation.StringRes

interface ResourceManager {
    fun getString(@StringRes resId : Int) : String
}

