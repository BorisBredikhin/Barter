package ru.bredikhinpechnnikov.barter.data.model

import org.json.JSONObject

class Task(json: String) {
    val obj = JSONObject(json)
    val title: String
        get() = obj.getString("title")
    val customer: Int
        get() = obj.getInt("customer")
}
