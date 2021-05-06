package ru.bredikhinpechnnikov.barter.net

import okhttp3.OkHttpClient
import okhttp3.Request
import ru.bredikhinpechnnikov.barter.data.model.Profile

fun getUserData(token: String): Profile {
    var response: Profile? = null
    var resp: String? = null

    val thr = Thread(kotlinx.coroutines.Runnable {
        OkHttpClient()
            .newCall(
                Request
                    .Builder()
                    .url(Config.BACKEND_ADDRESS + "/api/profile/")
                    .header("Authorization", "token $token")
                    .get()
                    .build()
            )
            .execute()
            .use {
                resp = it.body!!.string()
                response = Profile(resp!!)
            }
    })


    thr.start()
    thr.join()

    return response!!
}
