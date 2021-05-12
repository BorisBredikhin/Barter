package ru.bredikhinpechnnikov.barter.net

import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject
import ru.bredikhinpechnnikov.barter.data.Result
import ru.bredikhinpechnnikov.barter.data.model.LoggedInUser
import java.nio.charset.Charset

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
class AuthProvider {

    fun login(username: String, password: String): Result<LoggedInUser> {
            val map = HashMap<String, String>()

            map["username"] = username
            map["password"] = password

            val obj = JSONObject(map as Map<String, String>)
            val body = obj.toString().toRequestBody(JSON)

        var resp: String? = null
        val thr = Thread(kotlinx.coroutines.Runnable {
            OkHttpClient()
                .newCall(
                    Request
                        .Builder()
                        .url(Config.BACKEND_ADDRESS + "/api/auth/login/")
                        .header("X-Token", "unauthorized")
                        .post(body)
                        .build()
                )
                .execute()
                .use {
                    resp = JSONObject(it.body!!.source().readString(Charset.forName("utf8"))).getString("token")
                }
        })
        thr.start()
        thr.join()
        return Result.Success(LoggedInUser(token = resp!!))
    }

    fun logout() {
        // TODO: revoke authentication
    }

    fun register(
        firstName: String,
        lastName: String,
        username: String,
        birthday: String,
        primaryActivity: String,
        phoneNumber: String,
        password: String,
        repeatedPassword: String
    ): Result<String> {
        if (password != repeatedPassword)
            return Result.Error(IllegalArgumentException("passwords must be same"))

        val map = HashMap<String, String>()

        map["first_name"] = firstName
        map["last_name"] = lastName
        map["username"] = username
        map["birth_day"] = birthday
        map["primary_activity"] = primaryActivity
        map["phone_number"] = phoneNumber
        map["password"] = password

        val obj = JSONObject(map as Map<String, String>)
        val body = obj.toString().toRequestBody(JSON)
        val request = Request
            .Builder()
            .url(Config.BACKEND_ADDRESS + "/api/register/")
            .header("X-Token", "unauthorized")
            .post(body)
            .build()

        val thr = Thread(kotlinx.coroutines.Runnable {
            OkHttpClient()
                .newCall(request)
                .execute()
                .body

            TODO("Обработка ответа от сервера")
        })

        thr.start()

        thr.join()

        return Result.Success("Ok")
    }
}