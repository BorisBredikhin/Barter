package ru.bredikhinpechnnikov.barter.data.model

import org.json.JSONObject

class Task(
    var title: String,
    var description: String,
    var price: Int,
    var address: String
) {
}
