package com.vngrs.lightingtalk.common.dependencyinjection

import com.vngrs.lightingtalk.common.Constants
import com.vngrs.lightingtalk.networking.StackoverflowApi

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CompositionRoot {

    private var mRetrofit: Retrofit? = null

    private val retrofit: Retrofit?
        get() {
            if (mRetrofit == null) {
                mRetrofit = Retrofit.Builder()
                        .baseUrl(Constants.BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build()
            }
            return mRetrofit
        }

    val stackoverflowApi: StackoverflowApi
        get() = retrofit?.create(StackoverflowApi::class.java)!!
}
