package com.mik0war.complimentsApp

class ViewModel(private val model: Model) {
    private var callBack : TextProvider? = null

    fun init(callBack : TextProvider){
        this.callBack = callBack

        model.init(object : ResultCallBack{
            override fun provideSuccess(data: Compliment) =
                callBack.provideText(data.getComplimentUI())

            override fun provideError(error: JokeError) =
                callBack.provideText(error.getErrorMessage())

        })

    }

    fun getJoke(){
        model.getCompliment()
    }

    fun clear(){
        model.clear()
    }
}

interface TextProvider{
    fun provideText(text: String)
}