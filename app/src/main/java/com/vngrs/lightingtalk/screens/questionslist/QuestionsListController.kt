package com.vngrs.lightingtalk.screens.questionslist

import com.vngrs.lightingtalk.questions.FetchLastActiveQuestionsUseCase
import com.vngrs.lightingtalk.questions.Question
import com.vngrs.lightingtalk.screens.common.toastshelper.ToastsHelper
import com.vngrs.lightingtalk.screens.common.screensnavigator.ScreensNavigator

class QuestionsListController(private val mFetchLastActiveQuestionsUseCase: FetchLastActiveQuestionsUseCase,
                              private val mScreensNavigator: ScreensNavigator,
                              private val mToastsHelper: ToastsHelper) : QuestionsListViewMvc.Listener, FetchLastActiveQuestionsUseCase.Listener {

    private var mViewMvc: QuestionsListViewMvc? = null

    fun bindView(viewMvc: QuestionsListViewMvc) {
        mViewMvc = viewMvc
    }

    fun onStart() {
        mViewMvc!!.registerListener(this)
        mFetchLastActiveQuestionsUseCase.registerListener(this)

        mViewMvc!!.showProgressIndication()
        mFetchLastActiveQuestionsUseCase.fetchLastActiveQuestionsAndNotify()
    }

    fun onStop() {
        mViewMvc!!.unregisterListener(this)
        mFetchLastActiveQuestionsUseCase.unregisterListener(this)
    }

    override fun onQuestionClicked(question: Question) {
        mScreensNavigator.toDialogDetails(question.id)
    }

    override fun onLastActiveQuestionsFetched(questions: List<Question>) {
        mViewMvc!!.hideProgressIndication()
        mViewMvc!!.bindQuestions(questions)
    }

    override fun onLastActiveQuestionsFetchFailed() {
        mViewMvc!!.hideProgressIndication()
        mToastsHelper.showUseCaseError()
    }
}
