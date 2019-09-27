package com.alanger.peruappstest.models.VO

import java.io.Serializable

class ComentVO : Serializable {

    var id: Int = 0
    var body: String? = null
    var dateTime: String? = null
    var idPost: Int = 0

    constructor() {}

    constructor(id: Int, body: String, dateTime: String, idPost: Int) {
        this.id = id
        this.body = body
        this.dateTime = dateTime
        this.idPost = idPost
    }
}
