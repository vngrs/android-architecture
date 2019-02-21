package com.vngrs.lightingtalk.questions

import com.vngrs.lightingtalk.common.BaseObservable
import com.vngrs.lightingtalk.common.Constants
import com.vngrs.lightingtalk.networking.questions.QuestionSchema
import com.vngrs.lightingtalk.networking.questions.QuestionsListResponseSchema
import com.vngrs.lightingtalk.networking.StackoverflowApi

import java.util.ArrayList

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FetchLastActiveQuestionsUseCase(private val mStackoverflowApi: StackoverflowApi) : BaseObservable<FetchLastActiveQuestionsUseCase.Listener>() {

    interface Listener {
        fun onLastActiveQuestionsFetched(questions: List<Question>)
        fun onLastActiveQuestionsFetchFailed()
    }

    fun fetchLastActiveQuestionsAndNotify() {
        mStackoverflowApi.fetchLastActiveQuestions(Constants.QUESTIONS_LIST_PAGE_SIZE)
                .enqueue(object : Callback<QuestionsListResponseSchema> {
                    override fun onResponse(call: Call<QuestionsListResponseSchema>, response: Response<QuestionsListResponseSchema>) {
                        if (response.isSuccessful) {
                            notifySuccess(response.body()!!.questions)
                        } else {
                            notifyFailure()
                        }
                    }

                    override fun onFailure(call: Call<QuestionsListResponseSchema>, t: Throwable) {
                        notifyFailure()
                    }
                })
    }

    private fun notifyFailure() {
        for (listener in listeners) {
            listener.onLastActiveQuestionsFetchFailed()
        }
    }

    private fun notifySuccess(questionSchemas: List<QuestionSchema>) {
        val questions = ArrayList<Question>(questionSchemas.size)
        for (questionSchema in questionSchemas) {
            questions.add(Question(questionSchema.id, questionSchema.title))
        }
        for (listener in listeners) {
            listener.onLastActiveQuestionsFetched(questions)
        }
    }
}
