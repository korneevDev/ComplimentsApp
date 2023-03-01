package com.mik0war.complimentsApp

class TestCloudDataSource : CloudDataSource {
    private var count = 0
    override fun getCompliment(callBack: ComplimentCloudCallBack) {
        callBack.provide(Compliment("compliment text " + count++))
    }
}