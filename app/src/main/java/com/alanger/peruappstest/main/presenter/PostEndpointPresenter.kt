package com.alanger.peruappstest.main.presenter

import com.alanger.peruappstest.models.VO.PostVO

interface PostEndpointPresenter {
    fun tryGetAllPost() //Interactor
    fun showListPostSuccess(data: List<PostVO>)
    fun showPostSuccess(data: PostVO)
    fun showDataError(error: String)

    fun tryGetPost(id: Int)


}
