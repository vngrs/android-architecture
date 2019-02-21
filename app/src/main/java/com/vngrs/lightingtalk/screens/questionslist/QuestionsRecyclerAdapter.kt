package com.vngrs.lightingtalk.screens.questionslist

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup

import com.vngrs.lightingtalk.questions.Question
import com.vngrs.lightingtalk.screens.common.ViewMvcFactory
import com.vngrs.lightingtalk.screens.questionslist.questionslistitem.QuestionsListItemViewMvc

import java.util.ArrayList

class QuestionsRecyclerAdapter(private val mListener: Listener, private val mViewMvcFactory: ViewMvcFactory) : RecyclerView.Adapter<QuestionsRecyclerAdapter.MyViewHolder>(), QuestionsListItemViewMvc.Listener {

    private var mQuestions: List<Question> = ArrayList()

    interface Listener {
        fun onQuestionClicked(question: Question?)
    }

    class MyViewHolder(internal val mViewMvc: QuestionsListItemViewMvc) : RecyclerView.ViewHolder(mViewMvc.rootView)

    fun bindQuestions(questions: List<Question>) {
        mQuestions = ArrayList(questions)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val viewMvc = mViewMvcFactory.getQuestionsListItemViewMvc(parent)
        viewMvc.registerListener(this)
        return MyViewHolder(viewMvc)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.mViewMvc.bindQuestion(mQuestions[position])
    }

    override fun getItemCount(): Int {
        return mQuestions.size
    }

    override fun onQuestionClicked(question: Question?) {
        mListener.onQuestionClicked(question)
    }

}
