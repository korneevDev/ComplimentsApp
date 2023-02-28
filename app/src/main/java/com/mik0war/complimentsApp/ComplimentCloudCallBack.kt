package com.mik0war.complimentsApp

interface ComplimentCloudCallBack {
    fun provide(compliment: ComplimentServerModel)

    fun fail(error : ErrorType)
}

enum class ErrorType{
    NO_CONNECTION,
    SERVICE_UNAVAILABLE
}

