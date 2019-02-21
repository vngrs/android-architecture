package com.vngrs.lightingtalk.networking.questions

import com.google.gson.annotations.SerializedName

class QuestionsListResponseSchema(@field:SerializedName("items")
                                  val questions: List<QuestionSchema>)
