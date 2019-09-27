package com.alanger.peruappstest.main.presenter


import android.content.Context

import com.alanger.peruappstest.models.VO.PostVO
import com.alanger.peruappstest.main.interactor.PostEndpointInteractor
import com.alanger.peruappstest.main.interactor.PostEndpointInteractorImpl
import com.alanger.peruappstest.main.view.main.PostEndpointListFragment


class PostEndpointPresenterImpl(private val view: PostEndpointListFragment) : PostEndpointPresenter {
    private val interactor: PostEndpointInteractor
    private val ctx: Context

    init {
        this.ctx = view.activity!!
        interactor = PostEndpointInteractorImpl(ctx, this)
    }

    override fun tryGetAllPost() {
        view.disableInputs()
        interactor.tryGetData()
    }

    override fun showListPostSuccess(postVOList: List<PostVO>) {
        view.enableInputs()
        view.showListPostData(postVOList)
    }

    override fun showPostSuccess(data: PostVO) {
        view.enableInputs()
        view.goToPostDetail(data)
    }

    override fun showDataError(error: String) {
        view.enableInputs()
        view.showError(error)
    }

    override fun tryGetPost(id: Int) {
        interactor.tryGetPost(id)
        view.disableInputs()
    }
}
