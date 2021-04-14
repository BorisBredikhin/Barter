package ru.bredikhinpechnnikov.barter.data.model

import org.json.JSONObject

class Profile(json: String) {
    val obj = JSONObject(json)
    val firstName: String
        get() = obj.getString("first_name")
    val lastName: String
        get() = obj.getString("last_name")
    val username: String
        get() = obj.getString("username")
    val primaryActivity: String
        get() = obj.getString("primary_activity")
    val points: Int
        get() = obj.getInt("points")
    val ratingAsCustomer: Double
        get() = obj.getDouble("rating_as_customer")
    val ratingAsExecutor: Double
        get() = obj.getDouble("rating_as_executor")
}
