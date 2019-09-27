package com.alanger.peruappstest.main.interactor


import android.content.Context

import com.alanger.peruappstest.models.DAO.PostDAO
import com.alanger.peruappstest.models.VO.PostVO
import com.alanger.peruappstest.retrofit.models.RetroPost
import com.alanger.peruappstest.main.presenter.PostEndpointPresenter
import com.alanger.peruappstest.main.repository.PostEndpointRepository
import com.alanger.peruappstest.main.repository.PostEndpointRepositoryImpl

import java.util.ArrayList


class PostEndpointInteractorImpl(private val ctx: Context, private val presenter: PostEndpointPresenter) : PostEndpointInteractor {
    private val repository: PostEndpointRepository

    internal var TAG = PostEndpointInteractorImpl::class.java.simpleName

    init {
        this.repository = PostEndpointRepositoryImpl(this)
    }

    override fun tryGetData() {
        repository.queryPostList()
    }

    override fun showListPostSuccess(retroPostList: List<RetroPost>) {

        val postVOList = ArrayList<PostVO>()

        for (r in retroPostList) {

            val postVOTemp = PostVO(r.id, r.userId, r.title, r.body)
            postVOList.add(postVOTemp)
            //getData from db
        }

        if (postVOList.size > 0) {
            presenter.showListPostSuccess(postVOList)
        } else {
            presenter.showDataError("Sin Datos")
        }
    }

    override fun showPostSuccess(data: RetroPost) {
        val temp = PostDAO(ctx).selectById(data.id)
        if (temp == null) {
            presenter.showPostSuccess(PostVO(data.id, data.userId, data.title, data.body))
        } else {
            presenter.showPostSuccess(temp)
        }

        //getData from db

    }


    override fun showError(mensajeRespuesta: String) {
        presenter.showDataError(mensajeRespuesta)
    }

    override fun tryGetPost(id: Int) {
        repository.queryPost(id)
    }


}
