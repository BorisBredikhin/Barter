package ru.bredikhinpechnnikov.barter.data.model

import org.json.JSONObject

class Profile(json: String) {
    val obj = JSONObject(json)
    val firstName: String?
        get() = try {
            obj.getString("first_name")
        } catch (_: Exception) {
            null
        }
    val lastName: String?
        get() = try {
            obj.getString("last_name")
        } catch (_: Exception) {
            null
        }
    val username: String?
        get() = try {
            obj.getString("username")
        } catch (_: Exception) {
            null
        }
    val primaryActivity: String?
        get() = try {
            obj.getString("primary_activity")
        } catch (_: Exception) {
            null
        }
    val points: Int?
        get() = try {
            obj.getInt("points")
        } catch (_: Exception) {
            null
        }
    val ratingAsCustomer: Double?
        get() = try {
            obj.getDouble("rating_as_customer")
        } catch (_: Exception) {
            null
        }
    val ratingAsExecutor: Double?
        get() = try {
            obj.getDouble("rating_as_executor")
        } catch (_: Exception) {
            null
        }
}
