package com.alanger.peruappstest.models.VO

import com.google.gson.Gson
import com.alanger.peruappstest.retrofit.models.RetroPost

import java.io.Serializable
import java.util.ArrayList

class PostVO : RetroPost, Serializable {

    var isSaved: Boolean = false
    var comentVOList: List<ComentVO>

    constructor() : super(0, 0, "", "") {// cuando recien parseamos a de retrofit
        this.isSaved = false
        comentVOList = ArrayList()
    }

    constructor(id: Int, userId: Int, title: String, body: String) : super(userId, id, title, body) {// cuando recien parseamos a de retrofit
        this.isSaved = false
        comentVOList = ArrayList()
    }

    constructor(userId: Int, id: Int, title: String, body: String, isSaved: Boolean, comentVOList: List<ComentVO>) : super(userId, id, title, body) { // loq  devolvemos a las interfaces
        this.isSaved = isSaved
        this.comentVOList = comentVOList
        this.isSaved = isSaved
    }


    override fun toString(): String {
        return Gson().toJson(this)
    }
}
