package com.vngrs.lightingtalk.screens.questionslist.questionslistitem

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView

import com.vngrs.lightingtalk.R
import com.vngrs.lightingtalk.questions.Question
import com.vngrs.lightingtalk.screens.common.views.BaseObservableViewMvc

class QuestionsListItemViewMvcImpl(inflater: LayoutInflater, parent: ViewGroup?) : BaseObservableViewMvc<QuestionsListItemViewMvc.Listener>(), QuestionsListItemViewMvc {

    private val mTxtTitle: TextView

    private var mQuestion: Question? = null

    init {
        rootView = inflater.inflate(R.layout.layout_question_list_item, parent, false)

        mTxtTitle = findViewById(R.id.txt_title)
        rootView.setOnClickListener {
            for (listener in listeners) {
                listener.onQuestionClicked(mQuestion)
            }
        }
    }

    override fun bindQuestion(question: Question) {
        mQuestion = question
        mTxtTitle.text = question.title
    }
}
