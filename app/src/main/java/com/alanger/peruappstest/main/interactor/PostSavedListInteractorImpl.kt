package com.alanger.peruappstest.main.interactor

import android.content.Context

import com.alanger.peruappstest.main.presenter.PostSavedListPresenterImpl
import com.alanger.peruappstest.models.DAO.PostDAO

class PostSavedListInteractorImpl(private val ctx: Context, private val presenter: PostSavedListPresenterImpl) {

    internal var TAG = PostEndpointInteractorImpl::class.java.simpleName


    fun tryGetListFavorites() {
        val postDAO = PostDAO(ctx)
        presenter.showFavoriteList(postDAO.listAll())
    }


}
