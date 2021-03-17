package ru.bredikhinpechnnikov.barter.data

import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject
import ru.bredikhinpechnnikov.barter.Config
import ru.bredikhinpechnnikov.barter.JSON
import ru.bredikhinpechnnikov.barter.data.model.LoggedInUser
import java.io.IOException
import java.lang.Exception

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
class AuthDataSource {

    fun login(username: String, password: String): Result<LoggedInUser> {
        try {
            // TODO: handle loggedInUser authentication
            val fakeUser = LoggedInUser(java.util.UUID.randomUUID().toString(), "Jane Doe")
            return Result.Success(fakeUser)
        } catch (e: Throwable) {
            return Result.Error(IOException("Error logging in", e))
        }
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
    ): String {
        if (password != repeatedPassword)
            return ""

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
            .url(Config.BACKEND_ADDRESS+"/api/register")
            .header("X-Token", "unauthorized")
            .post(body)
            .build()

        val thr = Thread(kotlinx.coroutines.Runnable {
            var resp = OkHttpClient()
                .newCall(request)
                .execute()
                .body

            TODO("Обработка ответа от сервера")
        })

        thr.start()

        thr.join()

        return ""
    }
}