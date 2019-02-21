package com.vngrs.lightingtalk.common

import android.app.Application

import com.vngrs.lightingtalk.common.dependencyinjection.CompositionRoot

class CustomApplication : Application() {

    var compositionRoot: CompositionRoot? = null
        private set

    override fun onCreate() {
        super.onCreate()
        compositionRoot = CompositionRoot()
    }
}
