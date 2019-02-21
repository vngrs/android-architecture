package com.vngrs.lightingtalk.screens.common

import android.view.LayoutInflater
import android.view.ViewGroup

import com.vngrs.lightingtalk.screens.questiondetails.QuestionDetailsViewMvc
import com.vngrs.lightingtalk.screens.questiondetails.QuestionDetailsViewMvcImpl
import com.vngrs.lightingtalk.screens.questionslist.questionslistitem.QuestionsListItemViewMvc
import com.vngrs.lightingtalk.screens.questionslist.questionslistitem.QuestionsListItemViewMvcImpl
import com.vngrs.lightingtalk.screens.questionslist.QuestionsListViewMvc
import com.vngrs.lightingtalk.screens.questionslist.QuestionsListViewMvcImpl

class ViewMvcFactory(private val mLayoutInflater: LayoutInflater) {

    fun getQuestionsListViewMvc(parent: ViewGroup?): QuestionsListViewMvc {
        return QuestionsListViewMvcImpl(mLayoutInflater, parent, this)
    }

    fun getQuestionsListItemViewMvc(parent: ViewGroup?): QuestionsListItemViewMvc {
        return QuestionsListItemViewMvcImpl(mLayoutInflater, parent)
    }

    fun getQuestionDetailsViewMvc(parent: ViewGroup?): QuestionDetailsViewMvc {
        return QuestionDetailsViewMvcImpl(mLayoutInflater, parent)
    }
}
