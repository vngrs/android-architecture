package com.vngrs.lightingtalk.screens.questiondetails

import com.vngrs.lightingtalk.questions.QuestionDetails
import com.vngrs.lightingtalk.screens.common.views.ViewMvc

interface QuestionDetailsViewMvc : ViewMvc {

    fun bindQuestion(question: QuestionDetails)

    fun showProgressIndication()

    fun hideProgressIndication()
}
