package com.vngrs.lightingtalk.screens.questionslist

import android.os.Bundle

import com.vngrs.lightingtalk.screens.common.controllers.BaseActivity

class QuestionsListActivity : BaseActivity() {

    private var mQuestionsListController: QuestionsListController? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val viewMvc = compositionRoot.viewMvcFactory.getQuestionsListViewMvc(null)

        mQuestionsListController = compositionRoot.questionsListController
        mQuestionsListController!!.bindView(viewMvc)

        setContentView(viewMvc.rootView)
    }

    override fun onStart() {
        super.onStart()
        mQuestionsListController!!.onStart()
    }

    override fun onStop() {
        super.onStop()
        mQuestionsListController!!.onStop()
    }

}
