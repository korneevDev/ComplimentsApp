package com.mik0war.complimentsApp

sealed class Result<out R, out E>{
    data class Success<out T>(val data: T) : Result<T, Nothing>()
    data class Error<out S>(val errorType: S) : Result<Nothing, S>()
}
