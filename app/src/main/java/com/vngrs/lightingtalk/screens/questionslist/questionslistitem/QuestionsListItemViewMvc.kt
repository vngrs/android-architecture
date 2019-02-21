package com.vngrs.lightingtalk.screens.questionslist.questionslistitem

import com.vngrs.lightingtalk.questions.Question
import com.vngrs.lightingtalk.screens.common.views.ObservableViewMvc

interface QuestionsListItemViewMvc : ObservableViewMvc<QuestionsListItemViewMvc.Listener> {

    interface Listener {
        fun onQuestionClicked(question: Question?)
    }

    fun bindQuestion(question: Question)
}
