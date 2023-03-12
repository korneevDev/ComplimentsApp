package com.mik0war.complimentsApp.core.domain

import com.mik0war.complimentsApp.core.presentation.Failure

interface FailureHandler {
    fun handle(e : Exception) : Failure
}

