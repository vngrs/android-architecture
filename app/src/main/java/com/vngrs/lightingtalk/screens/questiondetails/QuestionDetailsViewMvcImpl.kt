package com.vngrs.lightingtalk.screens.questiondetails

import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView

import com.vngrs.lightingtalk.R
import com.vngrs.lightingtalk.questions.QuestionDetails
import com.vngrs.lightingtalk.screens.common.views.BaseViewMvc


class QuestionDetailsViewMvcImpl(inflater: LayoutInflater, container: ViewGroup?) : BaseViewMvc(), QuestionDetailsViewMvc {

    private val mTxtQuestionTitle: TextView
    private val mTxtQuestionBody: TextView
    private val mProgressBar: ProgressBar

    init {
        rootView = inflater.inflate(R.layout.layout_question_details, container, false)
        mTxtQuestionTitle = findViewById(R.id.txt_question_title)
        mTxtQuestionBody = findViewById(R.id.txt_question_body)
        mProgressBar = findViewById(R.id.progress)
    }

    override fun bindQuestion(question: QuestionDetails) {
        val questionTitle = question.title
        val questionBody = question.body

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            mTxtQuestionTitle.text = Html.fromHtml(questionTitle, Html.FROM_HTML_MODE_LEGACY)
            mTxtQuestionBody.text = Html.fromHtml(questionBody, Html.FROM_HTML_MODE_LEGACY)
        } else {
            mTxtQuestionTitle.text = Html.fromHtml(questionTitle)
            mTxtQuestionBody.text = Html.fromHtml(questionBody)
        }
    }

    override fun showProgressIndication() {
        mProgressBar.visibility = View.VISIBLE
    }

    override fun hideProgressIndication() {
        mProgressBar.visibility = View.GONE
    }
}
