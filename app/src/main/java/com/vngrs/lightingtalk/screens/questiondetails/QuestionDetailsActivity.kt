package com.vngrs.lightingtalk.screens.questiondetails

import android.content.Context
import android.content.Intent
import android.os.Bundle

import com.vngrs.lightingtalk.questions.FetchQuestionDetailsUseCase
import com.vngrs.lightingtalk.questions.QuestionDetails
import com.vngrs.lightingtalk.screens.common.controllers.BaseActivity
import com.vngrs.lightingtalk.screens.common.toastshelper.ToastsHelper

class QuestionDetailsActivity : BaseActivity(), FetchQuestionDetailsUseCase.Listener {

    private var mFetchQuestionDetailsUseCase: FetchQuestionDetailsUseCase? = null

    private var mToastsHelper: ToastsHelper? = null

    private var mViewMvc: QuestionDetailsViewMvc? = null

    private val questionId: String
        get() = intent.getStringExtra(EXTRA_QUESTION_ID)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mFetchQuestionDetailsUseCase = compositionRoot.fetchQuestionDetailsUseCase
        mToastsHelper = compositionRoot.messagesDisplayer
        mViewMvc = compositionRoot.viewMvcFactory.getQuestionDetailsViewMvc(null)

        setContentView(mViewMvc!!.rootView)
    }

    override fun onStart() {
        super.onStart()
        mFetchQuestionDetailsUseCase!!.registerListener(this)

        mViewMvc!!.showProgressIndication()
        mFetchQuestionDetailsUseCase!!.fetchQuestionDetailsAndNotify(questionId)
    }

    override fun onStop() {
        super.onStop()
        mFetchQuestionDetailsUseCase!!.unregisterListener(this)
    }

    override fun onQuestionDetailsFetched(questionDetails: QuestionDetails) {
        mViewMvc!!.hideProgressIndication()
        mViewMvc!!.bindQuestion(questionDetails)
    }

    override fun onQuestionDetailsFetchFailed() {
        mViewMvc!!.hideProgressIndication()
        mToastsHelper!!.showUseCaseError()
    }

    companion object {

        const val EXTRA_QUESTION_ID = "EXTRA_QUESTION_ID"

        fun start(context: Context, questionId: String) {
            val intent = Intent(context, QuestionDetailsActivity::class.java)
            intent.putExtra(EXTRA_QUESTION_ID, questionId)
            context.startActivity(intent)
        }
    }
}
