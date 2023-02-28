package com.mik0war.complimentsApp

interface CloudDataSource {
    fun getCompliment(callBack: ComplimentCloudCallBack)
}