package com.vngrs.lightingtalk.screens.questionslist

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar

import com.vngrs.lightingtalk.R
import com.vngrs.lightingtalk.questions.Question
import com.vngrs.lightingtalk.screens.common.views.BaseObservableViewMvc
import com.vngrs.lightingtalk.screens.common.ViewMvcFactory

class QuestionsListViewMvcImpl(inflater: LayoutInflater, parent: ViewGroup?, viewMvcFactory: ViewMvcFactory) : BaseObservableViewMvc<QuestionsListViewMvc.Listener>(), QuestionsListViewMvc, QuestionsRecyclerAdapter.Listener {

    private val mRecyclerQuestions: RecyclerView
    private val mAdapter: QuestionsRecyclerAdapter
    private val mProgressBar: ProgressBar

    init {
        rootView = inflater.inflate(R.layout.layout_questions_list, parent, false)
        mRecyclerQuestions = findViewById(R.id.recycler_questions)
        mRecyclerQuestions.layoutManager = LinearLayoutManager(context)
        mAdapter = QuestionsRecyclerAdapter(this, viewMvcFactory)
        mRecyclerQuestions.adapter = mAdapter

        mProgressBar = findViewById(R.id.progress)
    }

    override fun onQuestionClicked(question: Question?) {
        for (listener in listeners) {
            listener.onQuestionClicked(question!!)
        }
    }

    override fun bindQuestions(questions: List<Question>) {
        mAdapter.bindQuestions(questions)
    }

    override fun showProgressIndication() {
        mProgressBar.visibility = View.VISIBLE
    }

    override fun hideProgressIndication() {
        mProgressBar.visibility = View.GONE
    }
}
