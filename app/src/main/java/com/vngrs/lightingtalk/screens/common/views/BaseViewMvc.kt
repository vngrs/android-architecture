package com.vngrs.lightingtalk.screens.common.views

import android.content.Context
import android.view.View

abstract class BaseViewMvc : ViewMvc {

    override lateinit var rootView: View

    protected val context: Context
        get() = rootView.context

    protected fun <T : View> findViewById(id: Int): T {
        return rootView.findViewById(id)
    }
}
