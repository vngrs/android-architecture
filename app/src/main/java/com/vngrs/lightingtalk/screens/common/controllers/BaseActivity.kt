package com.vngrs.lightingtalk.screens.common.controllers

import android.support.v7.app.AppCompatActivity

import com.vngrs.lightingtalk.common.CustomApplication
import com.vngrs.lightingtalk.common.dependencyinjection.ControllerCompositionRoot

open class BaseActivity : AppCompatActivity() {

    private var mControllerCompositionRoot: ControllerCompositionRoot? = null

    protected val compositionRoot: ControllerCompositionRoot
        get() {
            if (mControllerCompositionRoot == null) {
                mControllerCompositionRoot = ControllerCompositionRoot(
                        (application as CustomApplication).compositionRoot!!,
                        this
                )
            }
            return mControllerCompositionRoot as ControllerCompositionRoot
        }

}
