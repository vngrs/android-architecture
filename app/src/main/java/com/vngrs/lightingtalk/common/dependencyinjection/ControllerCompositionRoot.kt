package com.vngrs.lightingtalk.common.dependencyinjection

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater

import com.vngrs.lightingtalk.networking.StackoverflowApi
import com.vngrs.lightingtalk.questions.FetchLastActiveQuestionsUseCase
import com.vngrs.lightingtalk.questions.FetchQuestionDetailsUseCase
import com.vngrs.lightingtalk.screens.common.toastshelper.ToastsHelper
import com.vngrs.lightingtalk.screens.common.screensnavigator.ScreensNavigator
import com.vngrs.lightingtalk.screens.common.ViewMvcFactory
import com.vngrs.lightingtalk.screens.questionslist.QuestionsListController

class ControllerCompositionRoot(private val mCompositionRoot: CompositionRoot, private val mActivity: Activity) {

    private val context: Context
        get() = mActivity

    private val stackoverflowApi: StackoverflowApi
        get() = mCompositionRoot.stackoverflowApi

    private val layoutInflater: LayoutInflater
        get() = LayoutInflater.from(context)

    val viewMvcFactory: ViewMvcFactory
        get() = ViewMvcFactory(layoutInflater)

    val fetchQuestionDetailsUseCase: FetchQuestionDetailsUseCase
        get() = FetchQuestionDetailsUseCase(stackoverflowApi)

    val fetchLastActiveQuestionsUseCase: FetchLastActiveQuestionsUseCase
        get() = FetchLastActiveQuestionsUseCase(stackoverflowApi)

    val questionsListController: QuestionsListController
        get() = QuestionsListController(
                fetchLastActiveQuestionsUseCase,
                screensNavigator,
                messagesDisplayer
        )

    val messagesDisplayer: ToastsHelper
        get() = ToastsHelper(context)

    private val screensNavigator: ScreensNavigator
        get() = ScreensNavigator(context)
}
