package com.alanger.peruappstest.main.interactor


import com.alanger.peruappstest.retrofit.models.RetroPost

interface PostEndpointInteractor {

    fun tryGetData()

    fun showListPostSuccess(data: List<RetroPost>)
    fun showPostSuccess(data: RetroPost)

    fun showError(mensajeRespuesta: String)
    fun tryGetPost(id: Int)
}
