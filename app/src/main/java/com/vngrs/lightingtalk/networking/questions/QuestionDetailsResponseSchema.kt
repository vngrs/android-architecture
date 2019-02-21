package com.vngrs.lightingtalk.networking.questions

import com.google.gson.annotations.SerializedName

class QuestionDetailsResponseSchema(question: QuestionSchema) {

    @SerializedName("items")
    private val mQuestions: List<QuestionSchema> = listOf(question)

    val question: QuestionSchema
        get() = mQuestions[0]

}
