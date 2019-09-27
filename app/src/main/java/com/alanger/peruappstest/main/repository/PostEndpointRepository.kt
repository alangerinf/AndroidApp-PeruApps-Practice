package com.alanger.peruappstest.main.repository

interface PostEndpointRepository {

    fun queryPostList() //Interactor
    fun queryPost(id: Int) //Interactor

}
