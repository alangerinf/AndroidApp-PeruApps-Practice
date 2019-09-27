package com.alanger.peruappstest.main.view.adapters

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

import com.alanger.peruappstest.models.VO.PostVO

import java.util.ArrayList

class LiveDataFavoritesListViewModel : ViewModel() {

    val favorites: LiveData<List<PostVO>>
        get() {
            if (favoritesListLiveData == null) {
                favoritesListLiveData = MutableLiveData()
                postFavoritesList = ArrayList()
                favoritesListLiveData!!.value = postFavoritesList
            }
            return favoritesListLiveData!!
        }

    fun addPost(postVO: PostVO) {

        var flag = false
        for (p in postFavoritesList!!) {
            if (p.id == postVO.id) {
                flag = true
                p.comentVOList = postVO.comentVOList
                break
            }
        }
        if (!flag) {//if not fount same post
            postFavoritesList!!.add(postVO)
        }

        favoritesListLiveData!!.value = postFavoritesList
    }

    fun removePost(postVO: PostVO) {

        for (p in postFavoritesList!!) {
            if (p.id == postVO.id) {
                postFavoritesList!!.remove(p)
                break
            }
        }
        postFavoritesList!!.remove(postVO)
        favoritesListLiveData!!.value = postFavoritesList
    }


    fun setFavoritesList(postFavorites: MutableList<PostVO>) {
        postFavoritesList = postFavorites
        favoritesListLiveData!!.value = postFavoritesList
    }

    companion object {

        private var favoritesListLiveData: MutableLiveData<List<PostVO>>? = MutableLiveData()
        private var postFavoritesList: MutableList<PostVO>? = null
    }


}

