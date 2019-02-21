package com.vngrs.lightingtalk.screens.common.screensnavigator

import android.content.Context

import com.vngrs.lightingtalk.screens.questiondetails.QuestionDetailsActivity

class ScreensNavigator(private val mContext: Context) {

    fun toDialogDetails(questionId: String) {
        QuestionDetailsActivity.start(mContext, questionId)
    }
}
