package com.vngrs.lightingtalk.questions

import com.vngrs.lightingtalk.common.BaseObservable
import com.vngrs.lightingtalk.networking.questions.QuestionDetailsResponseSchema
import com.vngrs.lightingtalk.networking.questions.QuestionSchema
import com.vngrs.lightingtalk.networking.StackoverflowApi

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FetchQuestionDetailsUseCase(private val mStackoverflowApi: StackoverflowApi) : BaseObservable<FetchQuestionDetailsUseCase.Listener>() {

    interface Listener {
        fun onQuestionDetailsFetched(questionDetails: QuestionDetails)
        fun onQuestionDetailsFetchFailed()
    }

    fun fetchQuestionDetailsAndNotify(questionId: String) {
        mStackoverflowApi.fetchQuestionDetails(questionId)
                .enqueue(object : Callback<QuestionDetailsResponseSchema> {
                    override fun onResponse(call: Call<QuestionDetailsResponseSchema>, response: Response<QuestionDetailsResponseSchema>) {
                        if (response.isSuccessful) {
                            notifySuccess(response.body()!!.question)
                        } else {
                            notifyFailure()
                        }
                    }

                    override fun onFailure(call: Call<QuestionDetailsResponseSchema>, t: Throwable) {
                        notifyFailure()
                    }
                })
    }

    private fun notifyFailure() {
        for (listener in listeners) {
            listener.onQuestionDetailsFetchFailed()
        }
    }

    private fun notifySuccess(questionSchema: QuestionSchema) {
        for (listener in listeners) {
            listener.onQuestionDetailsFetched(
                    QuestionDetails(
                            questionSchema.id,
                            questionSchema.title,
                            questionSchema.body
                    ))
        }
    }
}
