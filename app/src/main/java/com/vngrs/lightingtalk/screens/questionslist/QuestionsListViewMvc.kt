package com.vngrs.lightingtalk.screens.questionslist

import com.vngrs.lightingtalk.questions.Question
import com.vngrs.lightingtalk.screens.common.views.ObservableViewMvc

interface QuestionsListViewMvc : ObservableViewMvc<QuestionsListViewMvc.Listener> {

    interface Listener {
        fun onQuestionClicked(question: Question)
    }

    fun bindQuestions(questions: List<Question>)

    fun showProgressIndication()

    fun hideProgressIndication()

}
