package com.alanger.peruappstest.main.presenter

import android.content.Context

import com.alanger.peruappstest.main.interactor.PostSavedListInteractorImpl
import com.alanger.peruappstest.main.view.main.PostSavedListFragment
import com.alanger.peruappstest.models.VO.PostVO

class PostSavedListPresenterImpl(private val view: PostSavedListFragment) {
    private val interactor: PostSavedListInteractorImpl
    private val ctx: Context

    init {
        this.ctx = view.activity!!
        interactor = PostSavedListInteractorImpl(ctx, this)
    }

    fun tryGetListFavorites() {
        interactor.tryGetListFavorites()
    }


    fun showFavoriteList(listAll: List<PostVO>) {
        view.showFavoriteList(listAll as MutableList<PostVO>)
    }


}
